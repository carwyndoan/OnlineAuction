package miu.edu.auction.service;

import java.io.IOException;

public interface PaypalService {
    Integer createOrder(String paypal_id, Double orderAmount, String description, String local_confirm_url) throws IOException;
    void authorizeOrder(String token, String payer_id) throws IOException;
}
