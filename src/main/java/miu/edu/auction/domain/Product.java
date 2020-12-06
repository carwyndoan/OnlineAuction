package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @NotNull
    private String name;

    @NotNull
    private int active;

    @NotNull
    private int status;

    @NotNull
    private double bid_startprice;

    @NotNull
    private double bid_deposit;

    @NotNull
    private double bid_finalprice;

    @NotNull
    private int bid_status;

    @NotBlank
    private LocalDate bid_duedate;

    @NotBlank
    private LocalDate bid_payment_duedate;

    private String image_path;

    private String description;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Bidding> biddings;
}
