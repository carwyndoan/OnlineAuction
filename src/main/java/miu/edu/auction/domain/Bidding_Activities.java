package miu.edu.auction.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private double amount;

    private String description;

    @DateTimeFormat(pattern = "MM-dd-YYYY HH:mm:ss")
    private LocalDate bidding_date;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "bidding_id")
    private Bidding bidding;

    @ManyToOne
    @JoinColumn(name = "user_bidding_id")
    private User bidding_user;

}
