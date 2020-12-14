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
        PayPalData paypalData = paypalDataRepository.save(new PayPalData());
        HttpResponse<Order> response = new CreateOrder().createOrder(paypal_id,"USD", orderAmount, description, true);
        for (LinkDescription link : response.result().links()) {
            if(link.rel().equals("approve"))
                paypalData.setApproval_link(link.href());
        }
        paypalData.setOrder_id(response.result().id());
        paypalData.setAmount(orderAmount);
        paypalData.setLocal_confirm_url(confirm_url);
        paypalDataRepository.save(paypalData);
        return  paypalData.getPaypal_data_id();
    }

    @Override
    public void authorizeOrder(String token, String payer_id) throws IOException {
        PayPalData payPalData = paypalDataRepository.findPayPalDataByOrderId(token);
        HttpResponse<Order> response = new AuthorizeOrder().authorizeOrder(token, true);
        String authId = response.result().purchaseUnits().get(0).payments().authorizations().get(0).id();
        payPalData.setAuthorization_id(authId);

        // Confirm order with paypal
        HttpResponse<Capture> captureHttpResponse = new CaptureOrder().captureOrder(authId, true);
        payPalData.setConfirm_id(captureHttpResponse.result().id());

        paypalDataRepository.save(payPalData);
    }
}
