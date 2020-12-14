package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.dto.BiddingActivityRestDTO;
import miu.edu.auction.domain.Bidding_Activities;
import miu.edu.auction.repository.PaypalDataRepository;
import miu.edu.auction.repository.UserRepository;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.PaypalService;
import miu.edu.auction.utils.CommonUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/bid")
public class BiddingRestController {

    @Autowired
    BiddingService biddingService;

    @Autowired
    PaypalService paypalService;

    @Autowired
    PaypalDataRepository paypalDataRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Environment environment;

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


    @PostMapping(value = "/deposit", produces = "application/json")
    public PayPalData biddingDeposit(@RequestBody String js) throws IOException {
        JSONObject jsonObject = new JSONObject(js);
        //System.out.println(jsonObject);
        Integer bidding_id = jsonObject.getInt("bidding_id");
        Integer user_id =  jsonObject.getInt("user_id");
        Bidding bidding = biddingService.findByID(bidding_id);
        double deposit = bidding.getDeposit() > 0 ? bidding.getDeposit() : 0.01 * bidding.getStart_price();
        //PayPal step 1: create order
        Integer client_id = paypalService.createOrder(environment.getProperty("paypal.auction.buyer"), deposit, "deposit", "bid/item/" + bidding_id);
        PayPalData paypalData = paypalDataRepository.findById(client_id).get();
        paypalData.setBidding_id(bidding_id);
        paypalData.setUser_id(user_id);
        paypalDataRepository.save(paypalData);
        return paypalData;
    }
}
