package miu.edu.auction.service;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.BiddingDTO;

import java.util.List;

public interface BiddingService {

    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);
    List<Bidding> findByWinner(Integer user_id);
}
