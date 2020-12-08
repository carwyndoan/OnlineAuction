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

    @Column(columnDefinition = "double default 0")
    private double deposit;

    @Column(columnDefinition = "double default 0")
    private double remainingAmount;

    private LocalDate depositDate;

    private LocalDate paymentDate;

    private LocalDate shipDate;

    private LocalDate deliveryDate;

    private String street;

    private String city;

    private String state;

    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "user_payment_id")
    private User user_payment;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding biddingPayment;
}
