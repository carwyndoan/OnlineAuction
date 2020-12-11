package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Bidding_Activities;
import miu.edu.auction.domain.User;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/bid")
public class BiddingController {

    @Autowired
    BiddingService biddingService;

    @Autowired
    UserService userService;

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
    public String loadItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        Bidding bid = biddingService.findByID(id);
        Double maxBid = bid.getBidding_activities().stream().mapToDouble(Bidding_Activities::getAmount).max().orElse(0);
        Double yourBid = bid.getBidding_activities().stream()
                .filter(ba -> ba.getBidding_user().getUser_id() == user.getUser_id())
                .map(item -> item.getAmount())
                .findAny().orElse(0D);
        model.addAttribute("bid", bid);
        model.addAttribute("current_bid", maxBid);
        model.addAttribute("your_bid", yourBid);
        model.addAttribute("bidding_id", bid.getBidding_id());
        model.addAttribute("user_id", user.getUser_id());
        return "bidding/item";
    }
}
