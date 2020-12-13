package miu.edu.auction.controller;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;
import miu.edu.auction.dto.BiddingActivityDTO;
import miu.edu.auction.dto.InvoiceDTO;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bidding")
public class BiddingPaymentController {
    @Autowired
    BiddingService biddingService;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ServletContext servletContext;

    @GetMapping(value = {"/winbiddings"})
    public String loadWinBidding(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String userEmail = userDetails.getUsername();
        List<Bidding> list = biddingService.findByWinner(userEmail, LocalDateTime.now());
        model.addAttribute("winbiddings", list);
//        return "bidding/WinBidding";
        return "bidding/listwinbidding";
    }

    @GetMapping(value = {"/biddings"})
    public String loadAllBidding(@AuthenticationPrincipal UserDetails userDetails, Model model, @RequestParam(name = "year", required = false) Integer year, @RequestParam(name = "month", required = false) Integer month, HttpServletRequest request) {
        if ((year == null) || (month == null)) {
            year = 0;
            month = 0;
        }
        String userEmail = userDetails.getUsername();
        List<Bidding> list = biddingService.findByUserBidding(userEmail, month, year);
        model.addAttribute("allbiddings", list);
        String baseUrl = String.format("%s://%s:%d/login/", request.getScheme(), request.getServerName(), request.getServerPort());
//        return "bidding/AllBidding";
        return "bidding/listbidding";
    }

    @GetMapping(value = {"/paymentform/{id}"})
    public String loadPayment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        Bidding bidding = biddingService.findByID(id);
        Payment payment = paymentService.findPaymentByBiddingIDAndUser(Integer.valueOf(id), user.getUser_id());
        if (payment != null) {
            payment.setUser_payment(user);
            if (bidding != null) {
                payment.setBiddingPayment(bidding);
                payment.setRemainingAmount(bidding.getFinalprice() - bidding.getDeposit());
                payment.setStreet(null);
                payment.setCity(null);
                payment.setState(null);
                payment.setZipcode(null);
                payment.setReceiverName(null);
                payment.setPaymentDate(LocalDateTime.now());
            }
            model.addAttribute("payment", payment);
//            return "bidding/Payment";
            return "bidding/createpayment";
        }
        return "bidding/error";
    }

    @PostMapping(value = {"/payment"})
    public String savePayment(@AuthenticationPrincipal UserDetails userDetails, @Valid Payment payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bidding/createpayment";
//            return "bidding/Payment";
        Payment payment1 = paymentService.makePayment(payment);
        Bidding bidding = payment1.getBiddingPayment();
        bidding.setStatus(2);
        biddingService.saveBidding(bidding);
        return "redirect:/bidding/biddings";
    }

    @RequestMapping(value = {"/ship/{id}"})
    public String ship(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        Payment payment = paymentService.findPaymentByBiddingIDAndUser(Integer.valueOf(id), user.getUser_id());
        payment.setShipDate(LocalDateTime.now());
        paymentService.savePayment(payment);

        Bidding bidding = biddingService.findByID(id);
        bidding.setStatus(3);
        biddingService.saveBidding(bidding);

        return "redirect:/bidding/biddings";
    }

    @RequestMapping(value = {"/delivery/{id}"})
    public String delivery(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        Payment payment = paymentService.findPaymentByBiddingIDAndUser(Integer.valueOf(id), user.getUser_id());
        payment.setDeliveryDate(LocalDateTime.now());
        paymentService.savePayment(payment);

        Bidding bidding = biddingService.findByID(id);
        bidding.setStatus(4);
        biddingService.saveBidding(bidding);
        biddingService.paySeller(bidding.getBidding_id());

        return "redirect:/bidding/biddings";
    }


    @GetMapping(value = {"/activities/{bidding_id}"})
    public String loadHistories(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer bidding_id, @RequestParam(name = "year", required = false) Integer year, @RequestParam(name = "month", required = false) Integer month, Model model) {
        if ((year == null) || (month == null)) {
            year = 0;
            month = 0;
        }
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        List<BiddingActivityDTO> listDTO = biddingService.findBidingHistoriesByMonthAndYear(bidding_id, year, month);
        model.addAttribute("activities", listDTO);
        model.addAttribute("biddingid", bidding_id);
//        return "bidding/BiddingHistories";
        return "bidding/listbiddinghistory";
    }

    @GetMapping(value = {"/invoice/{id}"})
    public String invoice(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);
        InvoiceDTO dto = paymentService.makeInvoice(id, user.getUser_id());
        model.addAttribute("invoice", dto);
        System.out.println(dto.getProduct_Name() + " " + dto.getOrder_Name() + " " + dto.getShipping_Name() + " " + dto.getProduct_VendorName());
        //build template here
        return "bidding/invoice";
    }

    @ModelAttribute("months")
    public List<Integer> getMonths() {
        List<Integer> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(i);
        }
        return months;
    }

    @ModelAttribute("years")
    public List<Integer> getYears() {
        int startYear = LocalDate.now().getYear();
        List<Integer> years = new ArrayList<>();
        int i = startYear - 5;
        while (i <= startYear) {
            years.add(i);
            i++;
        }
        return years;
    }
}
