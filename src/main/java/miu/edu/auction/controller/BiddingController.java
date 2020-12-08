package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.BiddingDTO;
import miu.edu.auction.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bid")
public class BiddingController {

    @Autowired
    BiddingService biddingService;

    @GetMapping(value = "")
    public String loadBidding(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        System.out.println(userDetails);
        String userEmail = userDetails.getUsername();
        List<Bidding> bids = biddingService.findBiddingByCategory(0, userEmail);
        for (Bidding bid:bids
             ) {
            System.out.println(bid.getBidding_id() + " - " + bid.getBidding_activities());
        }
        model.addAttribute("bids", bids);

        return "bidding/index";
    }

    @GetMapping(value = "/item")
    public String loadItem(Model model) {
        System.out.println("come here");
        return "bidding/item";
    }
}
