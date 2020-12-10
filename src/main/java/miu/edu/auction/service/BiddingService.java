package miu.edu.auction.service;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.dto.BiddingActivityDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BiddingService {

    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);

    Double placeBid(Integer bidding_id, Integer user_id, Double bid);

    List<Bidding> findByWinner(String email, LocalDateTime paymentDate);

    List<Bidding> findByUserBidding(String email, Integer month, Integer year);

    Optional<Bidding> findByID(Integer key);

//    List<BiddingActivityDTO> findBidingHistories(Integer bidding_id);
    List<BiddingActivityDTO> findBidingHistoriesByMonthAndYear(Integer bidding_id, Integer year, Integer month);

    Boolean closeBidding(Integer bidding_id);//Setup winner, return deposit to others

    Boolean paySeller(Integer bidding_id);//pay for seller when delivered / after shiped 30 days

    Bidding saveBidding(Bidding bidding);
}
