package miu.edu.auction.service.impl;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.repository.BiddingRepository;
import miu.edu.auction.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    BiddingRepository biddingRepository;

    @Override
    public List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email) {
        return biddingRepository.findBiddingByCategory(category_id, exclude_email);
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
