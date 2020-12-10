package miu.edu.auction.service.impl;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;
import miu.edu.auction.dto.InvoiceDTO;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findPaymentByBiddingID(Integer biddingID, Integer userID) {
        return paymentRepository.findPaymentByBiddingID(biddingID, userID);
    }

    @Override
    public Payment chargeDeposit(Payment payment) {
        //TODO: payment with paypal
        payment.setDepositDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return  savePayment(payment);
    }

    @Override
    public Payment returnDeposit(Payment payment) {
        //TODO: payment with paypal
        //Update system
        payment.setReturnDeposit(payment.getDeposit());
        payment.setReturnDepositDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment payToSeller(Payment payment) {
        //TODO: payment with paypal
        //Update system
        payment.setPaySellerDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return  payment;
    }

    @Override
    public InvoiceDTO makeInvoice(Integer biddingID, Integer userID) {
        Payment payment = paymentRepository.findPaymentByBiddingID(biddingID, userID);
        return null;
    }


}
