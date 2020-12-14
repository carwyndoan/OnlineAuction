package miu.edu.auction.service.impl.paypal;

import java.io.IOException;

import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.repository.PaypalDataRepository;
import org.json.JSONObject;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.payments.CapturesRefundRequest;
import com.paypal.payments.LinkDescription;
import com.paypal.payments.Money;
import com.paypal.payments.Refund;
import com.paypal.payments.RefundRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class RefundOrder extends PayPalClient {

    @Autowired
    PaypalDataRepository paypalDataRepository;

    /**
     * Creating empty body for Refund request. This request body can be created with
     * correct values as per the need.
     *
     * @return OrderRequest request with empty body
     */
    private RefundRequest buildRequestBody(String captureId) {
        RefundRequest refundRequest = new RefundRequest();
        PayPalData payPalData = paypalDataRepository.findPayPalDataByConfirmId(captureId);
        Money money = new Money();
        money.currencyCode(payPalData.getCurrency());
        money.value("" + payPalData.getAmount());
        refundRequest.amount(money);
        return refundRequest;
    }

    /**
     * Method to Refund the Capture. valid capture Id should be passed.
     *
     * @param captureId Capture ID from authorizeOrder response
     * @param debug     true = print response data
     * @return HttpResponse<Capture> response received from API
     * @throws IOException Exceptions from API if any
     */
    private HttpResponse<Refund> refundOrder(String captureId, boolean debug) throws IOException {
        CapturesRefundRequest request = new CapturesRefundRequest(captureId);
        request.prefer("return=representation");
        request.requestBody(buildRequestBody(captureId));
        HttpResponse<Refund> response = client().execute(request);
        if (debug) {
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Status: " + response.result().status());
            System.out.println("Refund Id: " + response.result().id());
            System.out.println("Links: ");
            for (LinkDescription link : response.result().links()) {
                System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
            }
            System.out.println("Full response body:");
            System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
        }
        PayPalData payPalData = paypalDataRepository.findPayPalDataByConfirmId(captureId);
        payPalData.setRefund_id(response.result().id());
        paypalDataRepository.save(payPalData);
        return response;
    }

    public void refundOrder(String captureId) throws IOException {
        HttpResponse<Refund> response = refundOrder(captureId, true);
        PayPalData payPalData = paypalDataRepository.findPayPalDataByConfirmId(captureId);
        payPalData.setRefund_id(response.result().id());
        paypalDataRepository.save(payPalData);
    }
}
