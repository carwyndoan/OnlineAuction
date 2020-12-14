package miu.edu.auction.service;

import miu.edu.auction.domain.PayPalData;

import java.io.IOException;

public interface PaypalService {
    Integer createOrder(String paypal_id, Double orderAmount, String description, String local_confirm_url) throws IOException;
    void authorizeOrder(String token, String payer_id) throws IOException;
    PayPalData update(Integer clientID, Integer userID, Integer biddingID);
    void refundOrder(String confirm_id) throws IOException;
}
