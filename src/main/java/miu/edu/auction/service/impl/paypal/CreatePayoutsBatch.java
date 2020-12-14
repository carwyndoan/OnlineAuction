package miu.edu.auction.service.impl.paypal;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.payouts.*;
import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.PayPalOutData;
import miu.edu.auction.domain.Payment;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.repository.PaypalOutDataRepository;

public class CreatePayoutsBatch extends PayPalClient{

    /**
     * Creates a Payouts batch request(POST - /v1/payments/payouts) with 5 payout items
     * A maximum of 15000 payout items are supported in a single batch request
     *
     * @return Response for the create payouts call
     * @throws IOException when call to the api fails
     */
    private HttpResponse<CreatePayoutResponse> createPayout(PayoutsPostRequest request , boolean debug) throws IOException {
        HttpResponse<CreatePayoutResponse> response = client().execute(request);
        if(debug) {
            System.out.println("Response Body:");
            System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
        }

        return response;
    }

    public void createPayout(PaypalOutDataRepository paypalOutDataRepository, PaymentRepository paymentRepository, Integer payment_id, String paypal_id, String currency, Double amount, String description) throws IOException {
        PayoutsPostRequest request = buildRequestBody(paypal_id, currency, amount, description);
        HttpResponse<CreatePayoutResponse> response = createPayout(request, true);
        JSONObject jsonObject = new JSONObject(new Json().serialize(response.result()));
        String payout_batch_id = jsonObject.getJSONObject("batch_header").getString("payout_batch_id");
        String batch_status = jsonObject.getJSONObject("batch_header").getString("batch_status");
        while (batch_status.equals("PENDING")) {
            HttpResponse<PayoutBatch> resInquiry = getPayoutBatch(payout_batch_id, false);
            jsonObject = new JSONObject(new Json().serialize(resInquiry.result()));
            batch_status = jsonObject.getJSONObject("batch_header").getString("batch_status");
        }

        PayPalOutData payPalOutData = paypalOutDataRepository.save(new PayPalOutData());
        payPalOutData.setLocal_payment_id(payment_id);
        payPalOutData.setPaypal_id(paypal_id);
        payPalOutData.setPayout_batch_id(jsonObject.getJSONObject("batch_header").getString("payout_batch_id"));
        payPalOutData.setBatch_status(jsonObject.getJSONObject("batch_header").getString("batch_status"));
        payPalOutData.setTime_created(jsonObject.getJSONObject("batch_header").getString("time_created"));
        payPalOutData.setTime_completed(jsonObject.getJSONObject("batch_header").getString("time_completed"));
        payPalOutData.setTime_closed(jsonObject.getJSONObject("batch_header").getString("time_closed"));
        Payment payment = paymentRepository.findById(payment_id).get();
        payPalOutData.setBidding_id(payment.getBiddingPayment().getBidding_id());
        payPalOutData.setReceiver_id(payment.getSeller().getUser_id());
        paypalOutDataRepository.save(payPalOutData);
    }

    /**
     * Retrieves a Payouts batch details. Payouts batch retrieval api(GET - /v1/payments/payouts/<batch-id>) is a Paginated API
     * that supports retrieving a maximum of 1000 items per page. Batches having items more than that have to paginate
     * to get complete details. Check 'links' in the response for prev and next page URI's.
     * Add 'totalRequired' parameter to know the total number of pages available
     *
     * @param batchId the Id of a Payouts batch
     * @return the details of the Payouts Batch
     * @throws IOException when call to the api fails
     */
    private HttpResponse<PayoutBatch> getPayoutBatch(String batchId, boolean debug) throws IOException {
        PayoutsGetRequest request = new PayoutsGetRequest(batchId)
                //Optional parameters, maximum of 1000 items are retrieved by default.
                .page(1)
                .pageSize(10)
                .totalRequired(true);

        HttpResponse<PayoutBatch> response = client().execute(request);
        if(debug) {
            System.out.println("Response Body:");
            System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
        }

        return response;
    }

    /**
     * Builds a Payouts batch create request body with 5 payout items to receivers email
     *
     * @return Request payload for Payouts create(POST) request
     */
    private PayoutsPostRequest buildRequestBody(String paypal_id, String currency, Double amount, String description) {
        List<PayoutItem> items = IntStream
                .range(1, 2)
                .mapToObj(index -> new PayoutItem()
                        .senderItemId("1")
                        .note(description)
                        .recipientType("PAYPAL_ID")
                        .receiver(paypal_id)
                        .amount(new Currency()
                                .currency(currency)
                                .value("" + amount)))
                .collect(Collectors.toList());

        CreatePayoutRequest payoutBatch = new CreatePayoutRequest()
                .senderBatchHeader(new SenderBatchHeader()
                        .senderBatchId("" + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                        .emailMessage("You have received a payout! Thanks for using our service!")
                        .emailSubject("You have a payout!")
                        .note("Enjoy your Payout!!"))
                .items(items);

        return new PayoutsPostRequest()
                .requestBody(payoutBatch);
    }
}
