package miu.edu.auction.service;

import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.repository.PaypalOutDataRepository;

import java.io.IOException;

public interface PaypalService {
    Integer createOrder(String paypal_id, Double orderAmount, String description, String local_confirm_url) throws IOException;
    void authorizeOrder(String token, String payer_id) throws IOException;
    PayPalData update(Integer clientID, Integer userID, Integer biddingID);
    void refundOrder(String confirm_id) throws IOException;
    void payoutOrder(Integer payment_id, String paypal_id, String currency, Double amount, String description) throws  IOException;
}
