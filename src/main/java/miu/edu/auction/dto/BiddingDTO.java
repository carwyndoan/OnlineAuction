package miu.edu.auction.dto;

import lombok.Data;
import miu.edu.auction.domain.Bidding;

@Data
public class BiddingDTO extends Bidding {
    String image_url;
}
