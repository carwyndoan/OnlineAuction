package miu.edu.auction.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipping_id;

    @NotNull
    private int status; //0: Shipped, 1: Shipping, 2: Delivered

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding bidding;
}
