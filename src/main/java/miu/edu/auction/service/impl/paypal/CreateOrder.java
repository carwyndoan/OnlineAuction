package miu.edu.auction.service.impl.paypal;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.repository.PaypalDataRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateOrder extends PayPalClient{

    Double amount;
    String description;
    String currency;
    String paypal_id = "KVMX66GCP7FL2";

    /**
     * Method to create minimum required order body with <b>AUTHORIZE</b> intent
     *
     * @return OrderRequest with created order request
     */
    private OrderRequest buildMinimumRequestBody() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("AUTHORIZE");
        ApplicationContext applicationContext = new ApplicationContext()
                .cancelUrl("http://localhost:8080/bid/paypal").returnUrl("http://localhost:8080/bid/paypal");
        orderRequest.applicationContext(applicationContext);
        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .customId(paypal_id)
                .description(description)
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode(currency).value("" + amount));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    /**
     * Method to create order with minimum required payload
     *
     * @param debug true = print response data
     * @return HttpResponse<Order> response received from API
     * @throws IOException Exceptions from API if any
     */
    private HttpResponse<Order> createOrder(boolean debug) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.header("prefer","return=representation");
        request.requestBody(buildMinimumRequestBody());
        HttpResponse<Order> response = client().execute(request);
        if (debug) {
            if (response.statusCode() == 201) {
                System.out.println("Order with Minimum Payload: ");
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Status: " + response.result().status());
                System.out.println("Order ID: " + response.result().id());
                System.out.println("Intent: " + response.result().checkoutPaymentIntent());
                System.out.println("Links: ");
                for (LinkDescription link : response.result().links()) {
                    System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
                }
                System.out.println("Total Amount: " + response.result().purchaseUnits().get(0).amountWithBreakdown().currencyCode()
                        + " " + response.result().purchaseUnits().get(0).amountWithBreakdown().value());
                System.out.println("Full response body:");
                System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
            }
        }

        return response;
    }

    /**
     * Method to create order with minimum required payload
     *
     * @param debug true = print response data
     * @return HttpResponse<Order> response received from API
     * @throws IOException Exceptions from API if any
     */
    public Integer createOrder(PaypalDataRepository paypalDataRepository, String paypal_id, String currency, Double amount, String description, String confirm_url) throws IOException {
        this.currency = currency;
        this.amount = amount;
        this.description = description;
        this.paypal_id = paypal_id;
        HttpResponse<Order> response = createOrder(true);
        PayPalData paypalData = paypalDataRepository.save(new PayPalData());
        for (LinkDescription link : response.result().links()) {
            if(link.rel().equals("approve"))
                paypalData.setApproval_link(link.href());
        }
        paypalData.setOrder_id(response.result().id());
        paypalData.setAmount(amount);
        paypalData.setLocal_confirm_url(confirm_url);
        paypalData.setCurrency(currency);
        paypalDataRepository.save(paypalData);

        return paypalData.getPaypal_data_id();
    }
}
