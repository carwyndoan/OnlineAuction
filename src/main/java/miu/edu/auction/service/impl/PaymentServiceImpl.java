package miu.edu.auction.service.impl;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Payment deposit(Bidding bidding, User user) {
        Payment payment = new Payment();
        payment.setUser_payment(user);
        payment.setBiddingPayment(bidding);
        payment.setDeposit(bidding.getDeposit() <= 0 ? bidding.getStart_price() * 0.1 : bidding.getDeposit());
        return  savePayment(payment);
    }


}
