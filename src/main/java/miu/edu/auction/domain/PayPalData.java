package miu.edu.auction.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PayPalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paypal_data_id;
    // Step 1: generate order_id, approval_limk
    String order_id;
    String approval_link;
    // Step 2: get payer_id from paypal
    String payer_id;
    // Step 3: authorize order_id and get authoriation_id
    String authorization_id;
    // Step 4: confirm with paypal and get confirm_id
    String confirm_id;
    String local_confirm_url;
    Double amount;
    Integer local_payment_id;
    Integer user_id;
    Integer bidding_id;
}
