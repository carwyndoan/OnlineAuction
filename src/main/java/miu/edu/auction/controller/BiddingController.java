package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Bidding_Activities;
import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.domain.User;
import miu.edu.auction.dto.BiddingActivityDTO;
import miu.edu.auction.dto.BiddingActivityRestDTO;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.repository.PaypalDataRepository;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.PaymentService;
import miu.edu.auction.service.PaypalService;
import miu.edu.auction.service.UserService;
import miu.edu.auction.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/bid")
public class BiddingController {

    @Autowired
    BiddingService biddingService;

    @Autowired
    UserService userService;

    @Autowired
    PaypalService paypalService;

    @Autowired
    PaypalDataRepository paypalDataRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping(value = "")
    public String loadBidding(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        //System.out.println(userDetails);
        String userEmail = userDetails.getUsername();
        List<Bidding> bids = biddingService.findBiddingByCategory(1, userEmail);
//        for (Bidding bid:bids
//             ) {
//            System.out.println(bid.getProduct().getName() + " by " + bid.getProduct().getUser().getName());
//        }
        model.addAttribute("bids", bids);

        return "bidding/index";
    }

    @GetMapping(value = "/item/{id}")
    public String loadItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model) throws IOException {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        Bidding bid = biddingService.findByID(id);
        boolean checkDeposit = paypalDataRepository.findPayPalDataByBiddingId(id).size() > 0;
        Double maxBid = bid.getBidding_activities().stream().mapToDouble(Bidding_Activities::getAmount).max().orElse(bid.getStart_price());
        Double yourBid = bid.getBidding_activities().stream()
                .filter(ba -> ba.getBidding_user().getUser_id() == user.getUser_id())
                .mapToDouble(Bidding_Activities::getAmount)
                .max().orElse(0D);
        BiddingActivityRestDTO biddingActivityRestDTO = new BiddingActivityRestDTO();
        biddingActivityRestDTO.setCurrent_bid(maxBid);
        biddingActivityRestDTO.setBid_amount(yourBid);
        biddingActivityRestDTO.setCounterDueDate(CommonUtils.explainDuration(bid.getDuedate()));
        model.addAttribute("bid", bid);
        model.addAttribute("bid_activity", biddingActivityRestDTO);
        model.addAttribute("bidding_id", bid.getBidding_id());
        model.addAttribute("user_id", user.getUser_id());
        model.addAttribute("check_deposit", checkDeposit);
        //paypalService.payoutOrder(1, "LBAFK9H7G8YJS", "USD", 500D, "Test payout");
        return "bidding/item";
    }

    @GetMapping(value = "/paypal")
    public String depositItem(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String token, @RequestParam String PayerID, Model model) throws IOException {
        paypalService.authorizeOrder(token, PayerID);
        PayPalData payPalData = paypalDataRepository.findPayPalDataByOrderId(token);
        return "redirect:/" + payPalData.getLocal_confirm_url(); //"bidding/item";
    }
}
