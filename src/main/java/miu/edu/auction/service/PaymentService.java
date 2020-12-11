package miu.edu.auction.service;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;
import miu.edu.auction.dto.InvoiceDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment savePayment(Payment payment);

    Payment findPaymentByBiddingIDAndUser(Integer biddingID, Integer userID);

    Payment chargeDeposit(Payment payment);

    Payment returnDeposit(Payment payment);

    Payment payToSeller(Payment payment);

    InvoiceDTO makeInvoice(Integer biddingID, Integer userID);

    List<Payment> findPaymentByBidding(Integer bidding_id);
}
