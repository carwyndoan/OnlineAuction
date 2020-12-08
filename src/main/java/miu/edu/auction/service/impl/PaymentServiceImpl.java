package miu.edu.auction.service.impl;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
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
    public Payment findPaymentByBiddingID(Integer biddingID) {
        return paymentRepository.findPaymentByBiddingID(biddingID);
    }


}
