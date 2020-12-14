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
import miu.edu.auction.service.PaypalService;
import miu.edu.auction.service.impl.paypal.AuthorizeOrder;
import miu.edu.auction.service.impl.paypal.CaptureOrder;
import miu.edu.auction.service.impl.paypal.CreateOrder;
import miu.edu.auction.service.impl.paypal.RefundOrder;
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

    @Override
    public Integer createOrder(String paypal_id, Double orderAmount, String description, String confirm_url) throws IOException {
        return new CreateOrder().createOrder(paypal_id,"USD", orderAmount, description, confirm_url);
    }

    @Override
    public void authorizeOrder(String token, String payer_id) throws IOException {
        new AuthorizeOrder().authorizeOrder(token, payer_id);
    }

    @Override
    public void refundOrder(String confirm_id) throws IOException {
        new RefundOrder().refundOrder(confirm_id);
    }
}
