package miu.edu.auction.service.impl;

import miu.edu.auction.domain.*;
import miu.edu.auction.dto.BiddingActivityDTO;
import miu.edu.auction.repository.BiddingActivitiesRepository;
import miu.edu.auction.repository.BiddingRepository;
import miu.edu.auction.repository.UserRepository;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return biddingRepository.findBiddingByCategory(category_id, exclude_email);
    }

    /*
    * Return max bid
     */
    @Override
    public Double placeBid(Integer bidding_id, Integer user_id, Double bid) {
        Bidding bidding = biddingRepository.findById(bidding_id).get();
        User user = userRepository.findById(user_id).get();
        OptionalDouble max = bidding.getBidding_activities().stream()
                .filter(ba -> ba.getBidding_user().getUser_id() == user_id)
                .mapToDouble(Bidding_Activities::getAmount)
                .max();

        Bidding_Activities bidding_activities = new Bidding_Activities();
        bidding_activities.setBidding(bidding);
        bidding_activities.setBidding_user(user);
        bidding_activities.setBidding_date(LocalDateTime.now());

        if(!max.isPresent())
        {
            //Deposit
            paymentService.deposit(bidding, user);
            bidding_activities.setAmount(bid);
            biddingActivitiesRepository.save(bidding_activities);
        }
        else if(max.getAsDouble() < bid) {
            bidding_activities.setAmount(bid);
            biddingActivitiesRepository.save(bidding_activities);
        }

        return biddingActivitiesRepository.getMaxBiddingActivitiesByBidding(bidding.getBidding_id());
    }


    @Override
    public List<Bidding> findByWinner(String email, LocalDate paymentDate) {
        return biddingRepository.findByWinner(email, paymentDate);
    }

    @Override
    public Optional<Bidding> findByID(Integer key) {
        return biddingRepository.findById(key);
    }

    @Override
    public List<BiddingActivityDTO> findBidingHistories(Integer bidding_id) {
//        Optional<Bidding> bidding = biddingRepository.findById(bidding_id);
        List<Bidding_Activities> activitiesList = biddingActivitiesRepository.findByBidding(bidding_id);
        System.out.println("The number record of bidding history: "+ activitiesList.size());
        List<BiddingActivityDTO> listDto = new ArrayList<>();
        for (int i = 0; i <activitiesList.size(); i++){
            Bidding_Activities activity = activitiesList.get(i);
            BiddingActivityDTO biddingHistoryDTO = new BiddingActivityDTO();
            biddingHistoryDTO.setId(i);
            biddingHistoryDTO.setBiddingPrice(activity.getAmount());
            biddingHistoryDTO.setBiddingDate(activity.getBidding_date());
            biddingHistoryDTO.setUserName(activity.getBidding_user().getName());
            biddingHistoryDTO.setProductName(activity.getBidding().getProduct().getName());
            listDto.add(biddingHistoryDTO);
        }
        return null;
    }
}
