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
public class Bidding_Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bidding_activities_id;

    @NotNull
    private int activity;

    @NotNull
    private double amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding bidding;

    @ManyToOne
    @JoinColumn(name = "user_bidding_id")
    private User bidding_user;

}
