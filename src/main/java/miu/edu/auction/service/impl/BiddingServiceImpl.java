package miu.edu.auction.service.impl;

import miu.edu.auction.domain.*;
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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
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
        OptionalDouble max = bidding.getBidding_activities().stream()
                .filter(ba -> ba.getBidding_user().getUser_id() == user_id)
                .mapToDouble(Bidding_Activities::getAmount)
                .max();

        Bidding_Activities bidding_activities = new Bidding_Activities();
        bidding_activities.setBidding(bidding);
        bidding_activities.setBidding_user(user);
        bidding_activities.setBidding_date(LocalDate.now());

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
    public List<Bidding> findByWinner(String email) {
        return biddingRepository.findByWinner(email);
    }

    @Override
    public Optional<Bidding> findByID(Integer key) {
        return biddingRepository.findById(key);
    }
}
