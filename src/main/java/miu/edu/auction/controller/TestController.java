package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;
import miu.edu.auction.dto.BiddingActivityDTO;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.PaymentService;
import miu.edu.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Bidding> list = biddingService.findByWinner(userEmail, LocalDate.now());
        model.addAttribute("biddinglist", list);
        return "bidding/WinBidding";
    }

    @GetMapping(value = {"/paymentform/{id}"})
    public String loadPayment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        Optional<Bidding> bidding = biddingService.findByID(id);
        Payment payment = paymentService.findPaymentByBiddingID(Integer.valueOf(id), user.getUser_id());

        if (payment != null) {
            payment.setUser_payment(user);
            if (bidding.isPresent()) {
                payment.setBiddingPayment(bidding.get());
                payment.setRemainingAmount(bidding.get().getFinalprice() - bidding.get().getDeposit());
                payment.setPaymentDate(LocalDate.now());
            }
            model.addAttribute("payment", payment);
            return "bidding/Payment";
        }
        return "bidding/error";
    }

    @PostMapping(value = {"/payment"})
    public String savePayment(@Valid Payment payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bidding/Payment";
        paymentService.savePayment(payment);
        return "redirect:/bidding/winbiddings";
    }

    @GetMapping(value = {"/activities/{id}"})
    public String loadActivites(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        List<BiddingActivityDTO> listDTO = biddingService.findBidingHistoriesByMonthAndYear(id, -1, -1);
        int startYear = LocalDate.now().getYear();
        List<Integer> months = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for(int i = 1; i <=12; i++){
            months.add(i);
        }
        int i = startYear - 10;
        while (i <= startYear + 10){
            years.add(i);
            i++;
        }
        model.addAttribute("months", months);
        model.addAttribute("years", years);
        model.addAttribute("activities", listDTO);
        model.addAttribute("biddingid", id);
        return "bidding/BiddingHistories";
    }

    @GetMapping(value = {"/activitiesByMonthAndYear/{bidding_id}"})
    public String loadHistoryByYearAndMonth(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer bidding_id, @RequestParam Integer year, @RequestParam Integer month, Model model){
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        List<BiddingActivityDTO> listDTO = biddingService.findBidingHistoriesByMonthAndYear(bidding_id, year, month);
        int startYear = LocalDate.now().getYear();
        List<Integer> months = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for(int i = 1; i <=12; i++){
            months.add(i);
        }
        int i = startYear - 10;
        while (i <= startYear + 10){
            years.add(i);
            i++;
        }
        model.addAttribute("months", months);
        model.addAttribute("years", years);
        model.addAttribute("activities", listDTO);
        model.addAttribute("biddingid", bidding_id);
        return "bidding/BiddingHistories";
    }
}
