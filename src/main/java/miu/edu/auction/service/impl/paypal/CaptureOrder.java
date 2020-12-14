package miu.edu.auction.service.impl.paypal;

import java.io.IOException;

import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.repository.PaypalDataRepository;
import org.json.JSONObject;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.OrderRequest;
import com.paypal.payments.AuthorizationsCaptureRequest;
import com.paypal.payments.Capture;
import com.paypal.payments.LinkDescription;
import org.springframework.beans.factory.annotation.Autowired;

public class CaptureOrder extends PayPalClient{

    @Autowired
    PaypalDataRepository paypalDataRepository;
    /**
     * Creating empty body for capture request
     *
     * @return OrderRequest request with empty body
     */
    public OrderRequest buildRequestBody() {
        return new OrderRequest();
    }

    /**
     * Method to capture order after authorization
     *
     * @param authId Authorization ID from authorizeOrder response
     * @param debug  true = print response data
     * @return HttpResponse<Capture> response received from API
     * @throws IOException Exceptions from API if any
     */
    private HttpResponse<Capture> captureOrder(String authId, boolean debug) throws IOException {
        AuthorizationsCaptureRequest request = new AuthorizationsCaptureRequest(authId);
        request.requestBody(buildRequestBody());
        HttpResponse<Capture> response = client().execute(request);
        if (debug) {
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Status: " + response.result().status());
            System.out.println("Capture ID: " + response.result().id());
            System.out.println("Links: ");
            for (LinkDescription link : response.result().links()) {
                System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
            }
            System.out.println("Full response body:");
            System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
        }
        return response;
    }

    public void captureOrder(String authId) throws IOException {
        HttpResponse<Capture> response = captureOrder(authId, true);
        PayPalData payPalData = paypalDataRepository.findPayPalDataByAuthorizationId(authId);
        payPalData.setConfirm_id(response.result().id());
        paypalDataRepository.save(payPalData);
    }
}
