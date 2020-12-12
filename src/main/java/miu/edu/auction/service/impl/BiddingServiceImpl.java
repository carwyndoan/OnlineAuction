package miu.edu.auction.service.impl;

import miu.edu.auction.domain.*;
import miu.edu.auction.dto.BiddingActivityDTO;
import miu.edu.auction.repository.BiddingActivitiesRepository;
import miu.edu.auction.repository.BiddingRepository;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.repository.UserRepository;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.EmailService;
import miu.edu.auction.service.PaymentService;
import miu.edu.auction.utils.CommonUtils;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    BiddingRepository biddingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BiddingActivitiesRepository biddingActivitiesRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired // or @Inject
    private JobScheduler jobScheduler;

    @Autowired // or @Inject
    private BiddingService biddingService;

    @Override
    public List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email) {
        List<Bidding> biddingList = biddingRepository.findBiddingByCategory(category_id, exclude_email);
        for (Bidding bid : biddingList) {
            List<Bidding_Activities> biddingActivitiesList = biddingActivitiesRepository.findBidding_ActivitiesByBidding(bid.getBidding_id());
            bid.setBidding_activities(biddingActivitiesList);
        }
        return biddingList;
    }

    /*
     * Return max bid
     */
    @Override
    public Boolean placeBid(Integer bidding_id, Integer user_id, Double bid) {
        Bidding bidding = biddingRepository.findById(bidding_id).get();
        User user = userRepository.findById(user_id).get();
        List<Bidding_Activities> biddingActivitiesList = biddingActivitiesRepository.findBidding_ActivitiesByBidding(bidding_id);
        Double max = biddingActivitiesList.stream()
                .filter(ba -> ba.getBidding_user().getUser_id() == user_id)
                .mapToDouble(Bidding_Activities::getAmount)
                .max().orElse(bidding.getStart_price());

        if (bid < max) {
            throw new RuntimeException("Your bid must great than current bid !!!");
        }

        Bidding_Activities bidding_activities = new Bidding_Activities();
        bidding_activities.setBidding(bidding);
        bidding_activities.setBidding_user(user);
        bidding_activities.setBidding_date(LocalDateTime.now());

        if (biddingActivitiesList.stream().noneMatch(p -> p.getBidding_user().getUser_id() == user_id)) {
            //Deposit
            Payment payment = new Payment();
            payment.setUser_payment(user);
            payment.setBiddingPayment(bidding);
            payment.setDeposit(bidding.getDeposit() <= 0 ? bidding.getStart_price() * 0.1 : bidding.getDeposit());
            payment.setReceiverName("NA");
            payment.setStreet("NA");
            payment.setCity("NA");
            payment.setState("NA");
            payment.setZipcode("NA");
            paymentService.chargeDeposit(payment);

            bidding_activities.setAmount(bid);
            biddingActivitiesRepository.save(bidding_activities);
            bidding.getBidding_activities().add(bidding_activities);
        } else {
            bidding_activities.setAmount(bid);
            biddingActivitiesRepository.save(bidding_activities);
        }

        return true;
    }

    @Override
    public List<Bidding> findByWinner(String email, LocalDateTime paymentDate) {
        return biddingRepository.findByWinner(email, paymentDate);
    }

    @Override
    public List<Bidding> findByUserBidding(String email, Integer month, Integer year) {
//        System.out.println(year + " " + month);
        List<Bidding> list = biddingRepository.findByUserBidding(email).stream()
                .filter(bidding -> ((bidding.getStartdate() == null) || (bidding.getStartdate().getYear() == year) || (year == 0)))
                .filter(bidding -> ((bidding.getStartdate() == null) || (bidding.getStartdate().getMonthValue() == month) || (month == 0))).collect(Collectors.toList());
//        System.out.println("size: "+ list.size());
        return list;
    }

    @Override
    public Bidding findByID(Integer key) {
        Bidding bidding = biddingRepository.findById(key).orElse(null);
        List<Bidding_Activities> biddingActivities = biddingActivitiesRepository.findBidding_ActivitiesByBidding(key);
        List<Payment> paymentList = paymentService.findPaymentByBidding(key);
        if (bidding != null) {
            bidding.setBidding_activities(biddingActivities);
            bidding.setPayments(paymentList);
        }
        return bidding;
    }

    @Override
    public List<BiddingActivityDTO> findBidingHistoriesByMonthAndYear(Integer bidding_id, Integer year, Integer month) {
        List<Bidding_Activities> activitiesList = biddingActivitiesRepository.findByBidding(bidding_id);
        System.out.println("The number record of bidding history: " + activitiesList.size());
        List<BiddingActivityDTO> listDto = new ArrayList<>();
        for (int i = 0; i < activitiesList.size(); i++) {
            Bidding_Activities activity = activitiesList.get(i);
            if ((year > 0) && (month > 0)) {
                if ((activity.getBidding_date().getYear() != year) ||
                        (activity.getBidding_date().getMonthValue() != month)) {
                    continue;
                }
            } else if ((year > 0) && (month <= 0)) {
                if (activity.getBidding_date().getYear() != year) {
                    continue;
                }
            } else if ((year <= 0) && (month > 0)) {
                if (activity.getBidding_date().getMonthValue() != month)
                    continue;
            }
            BiddingActivityDTO biddingHistoryDTO = new BiddingActivityDTO();
            biddingHistoryDTO.setId(i);
            biddingHistoryDTO.setStartPrice(activity.getBidding().getStart_price());
            biddingHistoryDTO.setBiddingPrice(activity.getAmount());
            biddingHistoryDTO.setBiddingDate(activity.getBidding_date());
            biddingHistoryDTO.setUserName(activity.getBidding_user().getName());
            biddingHistoryDTO.setProductName(activity.getBidding().getProduct().getName());
            biddingHistoryDTO.setBiddingStartDate(activity.getBidding().getStartdate());
            biddingHistoryDTO.setBiddingDueDate(activity.getBidding().getDuedate());
            listDto.add(biddingHistoryDTO);
        }
        return listDto;
    }

    @Override
    public Boolean closeBidding(Integer bidding_id) {
        try {
            Double maxBid = biddingActivitiesRepository.getMaxBidByBidding(bidding_id);
            Bidding_Activities bidding_activities = biddingActivitiesRepository.getMaxBiddingActivity(bidding_id, maxBid);
            Bidding bidding = biddingRepository.findById(bidding_id).get();
            //set winner
            bidding.setWinner(bidding_activities.getBidding_user());
            bidding.setFinalprice(maxBid);

            //Return deposit
            for (Payment payment : bidding.getPayments()) {
                if (payment.getUser_payment().getUser_id() != bidding_activities.getBidding_user().getUser_id()) {
                    paymentService.returnDeposit(payment);
                } else {
                    User seller = userRepository.findById(bidding.getProduct().getUser().getUser_id()).get();
                    payment.setSeller(seller);
                    paymentService.savePayment(payment);
                }
            }

            biddingRepository.save(bidding);
            //start job check winner cancel
            jobScheduler.schedule(() -> biddingService.paySellerDeposit(bidding.getBidding_id()),
                    LocalDateTime.now().plusSeconds(CommonUtils.calculateDuration(bidding.getPayment_duedate())));

            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public Boolean paySeller(Integer bidding_id) {
        try {
            Bidding bidding = biddingRepository.findById(bidding_id).get();
            if (bidding.getStatus() == 3 || bidding.getStatus() == 4) {//pay to seller after ship 30 days or delivered
                Payment payment = paymentService.findPaymentByBidding(bidding_id).stream()
                        .filter(p -> p.getUser_payment().getUser_id() == bidding.getWinner().getUser_id())
                        .findFirst().get();
                paymentService.payToSeller(payment);
                bidding.setStatus(7);//Complete
                biddingRepository.save(bidding);
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public Bidding saveBidding(Bidding bidding) {
        if (bidding.getBidding_id() <= 0) {
            //Start auto close bidding
            jobScheduler.schedule(() -> biddingService.closeBidding(bidding.getBidding_id()),
                    LocalDateTime.now().plusSeconds(CommonUtils.calculateDuration(bidding.getDuedate())));
        }
        //Schedule if bidding status is Shipped
        if (bidding.getStatus() == 3) {
            //Start auto close bidding
            Bidding oldBidding = biddingRepository.findById(bidding.getBidding_id()).orElse(null);
            System.out.println("current status: " + oldBidding.getStatus());
//            jobScheduler.schedule(() -> biddingService.paySeller(bidding.getBidding_id()),
//                    LocalDateTime.now().plusSeconds(CommonUtils.calculateDuration(LocalDateTime.now().plusDays(30))));
            jobScheduler.schedule(() -> biddingService.paySeller(bidding.getBidding_id()),
                    LocalDateTime.now().plusSeconds(CommonUtils.calculateDuration(LocalDateTime.now().plusMinutes(1))));
        }


        return biddingRepository.save(bidding);
    }

    @Override
    public void paySellerDeposit(Integer bidding_id) {
        try {
            Bidding bidding = biddingRepository.findById(bidding_id).get();
            if (bidding.getStatus() == 1) {//Bidder has not pay full yet
                Payment payment = bidding.getPayments().stream()
                        .filter(p -> p.getUser_payment().getUser_id() == bidding.getWinner().getUser_id())
                        .findFirst().get();
                paymentService.paySellerDeposit(payment);
                bidding.setStatus(5);//Bidder cancel
                biddingRepository.save(bidding);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void returnBidderDeposit(Integer bidding_id) {
        try {
            Bidding bidding = biddingRepository.findById(bidding_id).get();
            if (bidding.getStatus() == 2) {//Seller has not ship yet
                Payment payment = bidding.getPayments().stream()
                        .filter(p -> p.getUser_payment().getUser_id() == bidding.getWinner().getUser_id())
                        .findFirst().get();
                paymentService.payBidderFull(payment); //return payment to Bidder in case not ship
                bidding.setStatus(6);//seller cancel
                biddingRepository.save(bidding);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
