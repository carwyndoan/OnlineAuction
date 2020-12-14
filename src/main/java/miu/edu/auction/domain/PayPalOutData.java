package miu.edu.auction.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PayPalOutData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paypal_out_id;

    String payout_batch_id;
    String batch_status;
    String time_created;
    String time_completed;
    String time_closed;
    String currency;
    Double amount;
    String paypal_id;
    Integer local_payment_id;
    Integer receiver_id;
    Integer bidding_id;
}
