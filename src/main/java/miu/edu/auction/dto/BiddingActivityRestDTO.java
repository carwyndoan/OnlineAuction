package miu.edu.auction.dto;

import lombok.Data;

@Data
public class BiddingActivityRestDTO {
    Integer bidding_id;
    Integer user_id;
    Double bid_amount;
    Double current_bid;
    Integer total_bidder;
    String counterDueDate;
}
