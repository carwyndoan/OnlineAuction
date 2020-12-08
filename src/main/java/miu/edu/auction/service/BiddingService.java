package miu.edu.auction.service;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.dto.BiddingHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BiddingService {

    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);

    Double placeBid(Integer bidding_id, Integer user_id, Double bid);

    List<Bidding> findByWinner(String email, LocalDate paymentDate);

    Optional<Bidding> findByID(Integer key);

    List<BiddingHistory> findBidingHistories(Integer bidding_id);
}
