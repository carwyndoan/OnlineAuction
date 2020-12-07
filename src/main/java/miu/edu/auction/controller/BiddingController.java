package miu.edu.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bid")
public class BiddingController {

    @GetMapping(value = "")
    public String loadBidding(Model model) {
        System.out.println("come here");
        return "bidding/list";
    }

    @GetMapping(value = "/item")
    public String loadItem(Model model) {
        System.out.println("come here");
        return "bidding/item";
    }
}
