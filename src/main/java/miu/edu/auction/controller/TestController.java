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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.time.LocalDate;
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
    public String loadPayment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model) {
//        int bID = Integer.parseInt(id);
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
//        System.out.println("UserID: " + user.getEmail());

        Optional<Bidding> bidding = biddingService.findByID(id);
//        System.out.println("Bidding: " + bidding.get().getBidding_id());

        Payment payment = paymentService.findPaymentByBiddingID(Integer.valueOf(id));
//        System.out.println("PaymentID: " + payment.getPayment_id());
        if (payment != null) {
            payment.setUser_payment(user);
            if (bidding.isPresent()) {
                payment.setBiddingPayment(bidding.get());
                payment.setRemainingAmount(bidding.get().getFinalprice() - bidding.get().getDeposit());
                payment.setPaymentDate(LocalDate.now());
            }
        }
        model.addAttribute("payment", payment);
        return "bidding/Payment";
    }

    @PostMapping(value = {"/payment"})
    public String savePayment(@Valid Payment payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bidding/Payment";
        paymentService.savePayment(payment);
        return "redirect:/bidding/winbiddings";
    }
}
