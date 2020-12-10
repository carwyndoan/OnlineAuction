package miu.edu.auction.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(columnDefinition = "double default 0")
    private double returnDeposit;

    private LocalDateTime depositDate;

    private LocalDateTime paymentDate;

    private LocalDateTime returnDepositDate;

    private LocalDateTime shipDate;

    private LocalDateTime deliveryDate;

    private LocalDateTime paySellerDate;

    @NotBlank
    private String receiverName;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    @Size(min = 2, max = 2, message = "{Size.state.validation}")
    private String state;

    @NotBlank
    private String zipcode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_payment_id")
    private User user_payment;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding biddingPayment;

    @OneToOne
    @JoinColumn(name = "seller_payment_id")
    private User seller;
}
