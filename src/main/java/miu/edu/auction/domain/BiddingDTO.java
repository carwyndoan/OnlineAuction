package miu.edu.auction.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiddingDTO extends Bidding {
    int totalBidder;
}
