package miu.edu.auction.service.impl;

import miu.edu.auction.domain.*;
import miu.edu.auction.dto.BiddingActivityDTO;
import miu.edu.auction.repository.BiddingActivitiesRepository;
import miu.edu.auction.repository.BiddingRepository;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.repository.UserRepository;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email) {
        return biddingRepository.findBiddingByCategory(category_id, exclude_email);
    }

    /*
    * Return max bid
     */
    @Override
    public Double placeBid(Integer bidding_id, Integer user_id, Double bid) {
        Bidding bidding = biddingRepository.findById(bidding_id).get();
        User user = userRepository.findById(user_id).get();
        OptionalDouble max = biddingActivitiesRepository.findBidding_ActivitiesByBidding(bidding_id).stream()
                .filter(ba -> ba.getBidding_user().getUser_id() == user_id)
                .mapToDouble(Bidding_Activities::getAmount)
                .max();

        Bidding_Activities bidding_activities = new Bidding_Activities();
        bidding_activities.setBidding(bidding);
        bidding_activities.setBidding_user(user);
        bidding_activities.setBidding_date(LocalDateTime.now());

        if (!max.isPresent()) {
            //Deposit
            Payment payment = new Payment();
            payment.setUser_payment(user);
            payment.setBiddingPayment(bidding);
            payment.setDeposit(bidding.getDeposit() <= 0 ? bidding.getStart_price() * 0.1 : bidding.getDeposit());
            paymentService.chargeDeposit(payment);

            bidding_activities.setAmount(bid);
            biddingActivitiesRepository.save(bidding_activities);
        } else if (max.getAsDouble() < bid) {
            bidding_activities.setAmount(bid);
            biddingActivitiesRepository.save(bidding_activities);
        }

        return biddingActivitiesRepository.getMaxBidByBidding(bidding.getBidding_id());
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
    public Optional<Bidding> findByID(Integer key) {
        return biddingRepository.findById(key);
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
            for (Payment payment:bidding.getPayments()) {
                if(payment.getUser_payment().getUser_id() != bidding_activities.getBidding_user().getUser_id()) {
                    paymentService.returnDeposit(payment);
                }
                else {
                    User seller = userRepository.findById(bidding.getProduct().getUser().getUser_id()).get();
                    payment.setSeller(seller);
                    paymentService.savePayment(payment);
                }
            }

            biddingRepository.save(bidding);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public Boolean paySeller(Integer bidding_id) {
        try {
            Bidding bidding = biddingRepository.findById(bidding_id).get();
            Payment payment = bidding.getPayments().stream()
                    .filter(p -> p.getUser_payment().getUser_id() == bidding.getWinner().getUser_id())
                    .findFirst().get();
            paymentService.payToSeller(payment);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public Bidding saveBidding(Bidding bidding) {
        return biddingRepository.save(bidding);
    }
}
