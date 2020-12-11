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
import miu.edu.auction.service.UserService;
import org.jobrunr.scheduling.BackgroundJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
        List<Bidding> biddingList = biddingRepository.findBiddingByCategory(category_id, exclude_email);
        for (Bidding bid:biddingList) {
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
        try {
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
                bidding.getBidding_activities().add(bidding_activities);
            } else if (max.getAsDouble() < bid) {
                bidding_activities.setAmount(bid);
                biddingActivitiesRepository.save(bidding_activities);
            }

            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public List<Bidding> findByWinner(String email, LocalDate paymentDate) {
        return biddingRepository.findByWinner(email, paymentDate);
    }

    @Override
    public List<Bidding> findByUserBidding(String email) {
        return biddingRepository.findByUserBidding(email);
    }

    @Override
    public Bidding findByID(Integer key) {
        Bidding bidding = biddingRepository.findById(key).orElse(null);
        List<Bidding_Activities> biddingActivities = biddingActivitiesRepository.findBidding_ActivitiesByBidding(key);
        List<Payment> paymentList = paymentService.findPaymentByBidding(key);
        if(bidding != null) {
            bidding.setBidding_activities(biddingActivities);
            bidding.setPayments(paymentList);
        }
        return bidding;
    }

//    @Override
//    public List<BiddingActivityDTO> findBidingHistories(Integer bidding_id) {
//        List<Bidding_Activities> activitiesList = biddingActivitiesRepository.findByBidding(bidding_id);
//        System.out.println("The number record of bidding history: " + activitiesList.size());
//        List<BiddingActivityDTO> listDto = new ArrayList<>();
//        for (int i = 0; i < activitiesList.size(); i++) {
//            Bidding_Activities activity = activitiesList.get(i);
//            BiddingActivityDTO biddingHistoryDTO = new BiddingActivityDTO();
//            biddingHistoryDTO.setId(i);
//            biddingHistoryDTO.setStartPrice(activity.getBidding().getStart_price());
//            biddingHistoryDTO.setBiddingPrice(activity.getAmount());
//            biddingHistoryDTO.setBiddingDate(activity.getBidding_date());
//            biddingHistoryDTO.setUserName(activity.getBidding_user().getName());
//            biddingHistoryDTO.setProductName(activity.getBidding().getProduct().getName());
//            biddingHistoryDTO.setBiddingDueDate(activity.getBidding().getDuedate());
//            listDto.add(biddingHistoryDTO);
//        }
//        return listDto;
//    }

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
}
