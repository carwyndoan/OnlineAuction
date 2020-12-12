package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.dto.BiddingActivityRestDTO;
import miu.edu.auction.domain.Bidding_Activities;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/bid")
public class BiddingRestController {

    @Autowired
    BiddingService biddingService;

    @PostMapping(value = "/placebid", produces = "application/json")
    public BiddingActivityRestDTO placeBid(@Valid @RequestBody BiddingActivityRestDTO biddingActivityRestDTO) {
        boolean bUpdate = biddingService.placeBid(biddingActivityRestDTO.getBidding_id(), biddingActivityRestDTO.getUser_id(), biddingActivityRestDTO.getBid_amount());
        if(bUpdate){
            Bidding bidding = biddingService.findByID(biddingActivityRestDTO.getBidding_id());
            Double maxBid = bidding.getBidding_activities().stream()
                    .mapToDouble(Bidding_Activities::getAmount).max().orElse(biddingActivityRestDTO.getBid_amount());
            biddingActivityRestDTO.setCurrent_bid(maxBid);
            biddingActivityRestDTO.setTotal_bidder(bidding.getBidding_activities().size());
            biddingActivityRestDTO.setCounterDueDate(CommonUtils.explainDuration(bidding.getDuedate()));
        }
        return biddingActivityRestDTO;
    }
}
