package miu.edu.auction.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    @NotNull
    private double amount;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int type; //0: deposit, 1: bidding, 2: full payment after winning, 3: system payment for seller

    @ManyToOne
    @JoinColumn(name = "user_payment_id")
    private User user_payment;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding biddingPayment;
}
