package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.PaymentService;
import miu.edu.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bidding")
public class TestController {
    @Autowired
    BiddingService biddingService;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = {"/winbiddings"})
    public String loadBidding(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        System.out.println(userDetails);
        String userEmail = userDetails.getUsername();
        List<Bidding> list = biddingService.findByWinner(userEmail);
        model.addAttribute("biddinglist", list);
        return "bidding/WinBidding";
    }

    @GetMapping(value = {"/paymentform/{id}"})
    public String loadPayment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, @ModelAttribute("payment") Payment payment) {
//        int bID = Integer.parseInt(id);
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        System.out.println("UserID: " + user.getEmail());

        Optional<Bidding> bidding = biddingService.findByID(id);
        System.out.println("Bidding: "+ bidding.get().getBidding_id());

        Payment p = paymentService.findPaymentByBiddingID(Integer.valueOf(id));
        System.out.println("PaymentID: " + p.getPayment_id());
        if (p != null) {
            payment = p;
            payment.setUser_payment(user);
            if (bidding.isPresent())
                payment.setBiddingPayment(bidding.get());
        }
        return "bidding/Payment";
    }
}
