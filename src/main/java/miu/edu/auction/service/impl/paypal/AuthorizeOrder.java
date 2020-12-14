package miu.edu.auction.service.impl.paypal;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersAuthorizeRequest;
import com.paypal.payments.Capture;
import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.repository.PaypalDataRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class AuthorizeOrder extends PayPalClient{

    @Autowired
    PaypalDataRepository paypalDataRepository;
    /**
     * Building empty request body. This can be updated with required fields as per
     * need.
     *
     * @return OrderActionRequest with empty body
     */
    private OrderRequest buildRequestBody() {
        return new OrderRequest();
    }

    /**
     * Method to authorize order after creation
     *
     * @param orderId Valid Approved Order ID from createOrder response
     * @param debug   true = print response data
     * @return HttpResponse<Order> response received from API
     * @throws IOException Exceptions from API if any
     */
    private HttpResponse<Order> authorizeOrder(String orderId, boolean debug) throws IOException {
        OrdersAuthorizeRequest request = new OrdersAuthorizeRequest(orderId);
        request.requestBody(buildRequestBody());
        HttpResponse<Order> response = client().execute(request);
        if (debug) {
            System.out.println("Authorization Ids:");
            response.result().purchaseUnits().forEach(purchaseUnit -> purchaseUnit.payments().authorizations().stream()
                    .map(authorization -> authorization.id()).forEach(System.out::println));
            System.out.println("Link Descriptions: ");
            for (LinkDescription link : response.result().links()) {
                System.out.println("\t" + link.rel() + ": " + link.href());
            }
            System.out.println("Full response body:");
            System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
        }
        return response;
    }

    public void authorizeOrder(String orderId, String payer_id) throws IOException {
        HttpResponse<Order> response = authorizeOrder(orderId, true);

        PayPalData payPalData = paypalDataRepository.findPayPalDataByOrderId(orderId);
        String authId = response.result().purchaseUnits().get(0).payments().authorizations().get(0).id();
        payPalData.setAuthorization_id(authId);
        payPalData.setPayer_id(payer_id);
        paypalDataRepository.save(payPalData);

        // Confirm order with paypal
        new CaptureOrder().captureOrder(authId);
    }
}
