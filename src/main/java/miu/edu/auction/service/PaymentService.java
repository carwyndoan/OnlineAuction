package miu.edu.auction.service;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;

import java.util.Optional;

public interface PaymentService {
    Payment savePayment(Payment payment);

    Payment findPaymentByBiddingID(Integer biddingID);

    Payment deposit(Bidding bidding, User user);
}
