package miu.edu.auction.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bidding_id;

    @NotNull
    private double start_price;

    @NotNull
    private double deposit;

    private double finalprice;

    @NotNull
    private int status; //0: inactive, 1: active //2: Payment //3: Shipped //4: Delivery //5: bidder cancel //6: seller cancel

    @DateTimeFormat(pattern = "MM-dd-YYYY")
    private LocalDateTime startdate;

    @DateTimeFormat(pattern = "MM-dd-YYYY")
    private LocalDateTime duedate;

    @DateTimeFormat(pattern = "MM-dd-YYYY")
    private LocalDateTime payment_duedate;

    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private User winner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bidding")
    private List<Bidding_Activities> bidding_activities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biddingPayment")
    private List<Payment> payments;
}
