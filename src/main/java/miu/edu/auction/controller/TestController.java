package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.User;
import miu.edu.auction.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TestController {
    @Autowired
    BiddingService biddingService;

    @GetMapping(value = {"/test"})
    public String test() {
        List<Bidding> listBidding = biddingService.findByWinner(Integer.valueOf(4));
        System.out.println("--------------: " + listBidding);
        System.out.println("The list of Bidding is: "+ listBidding.size());
        return "/registration/success";
    }
}
