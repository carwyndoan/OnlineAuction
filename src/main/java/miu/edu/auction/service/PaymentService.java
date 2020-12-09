package miu.edu.auction.service;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment savePayment(Payment payment);

    Payment findPaymentByBiddingID(Integer biddingID, Integer userID);

    Payment chargeDeposit(Payment payment);

    Payment returnDeposit(Payment payment);

    Payment payToSeller(Payment payment);
}
