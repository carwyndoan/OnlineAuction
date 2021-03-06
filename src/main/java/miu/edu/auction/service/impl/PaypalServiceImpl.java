package miu.edu.auction.service.impl;

//import com.paypal.api.payments.*;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
import com.paypal.http.HttpResponse;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Order;
import com.paypal.payments.Capture;
import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.repository.PaypalDataRepository;
import miu.edu.auction.repository.PaypalOutDataRepository;
import miu.edu.auction.service.PaypalService;
import miu.edu.auction.service.impl.paypal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaypalServiceImpl implements PaypalService {

    @Autowired
    Environment environment;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaypalDataRepository paypalDataRepository;

    @Autowired
    PaypalOutDataRepository paypalOutDataRepository;

    @Override
    public Integer createOrder(String paypal_id, Double orderAmount, String description, String confirm_url) throws IOException {
        return new CreateOrder().createOrder(paypalDataRepository, paypal_id,"USD", orderAmount, description, confirm_url);
    }

    @Override
    public void authorizeOrder(String token, String payer_id) throws IOException {
        new AuthorizeOrder().authorizeOrder(paypalDataRepository, token, payer_id);
    }

    @Override
    public void refundOrder(String confirm_id) throws IOException {
        new RefundOrder().refundOrder(paypalDataRepository, confirm_id);
    }

    @Override
    public void payoutOrder(Integer payment_id, String paypal_id, String currency, Double amount, String description) throws IOException {
        new CreatePayoutsBatch().createPayout(paypalOutDataRepository, paymentRepository, payment_id, paypal_id, currency, amount, description);
    }

    @Override
    public PayPalData update(Integer clientID, Integer userID, Integer biddingID, Integer paymentID) {
        PayPalData paypalData = paypalDataRepository.findById(clientID).get();
        paypalData.setBidding_id(biddingID);
        paypalData.setUser_id(userID);
        paypalData.setLocal_payment_id(paymentID);
        return paypalDataRepository.save(paypalData);
    }
}
