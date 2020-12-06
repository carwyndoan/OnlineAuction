package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bidding_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bidding_detail_id;

    @NotNull
    private int activity;

    @NotNull
    private double amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding bidding;

}
