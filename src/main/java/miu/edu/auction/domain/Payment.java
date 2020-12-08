package miu.edu.auction.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    private double deposit;

    private double remainingAmount;

    private LocalDate depositDate;

    private LocalDate paymentDate;

    private LocalDate shipDate;

    private LocalDate deliveryDate;

    private String street;

    private String city;

    private String state;

    private String zipcode;

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
