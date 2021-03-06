package miu.edu.auction.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    private int product_id;
    @NotNull
    private String name;
    // @NotNull
    private int active;
   // @NotNull
    private int status; //0: Save without release, 1: Save and Release
     private String image_path;
    //@NotBlank(message="you have to insert a value")
    private Long price;
    //@NotBlank(message="you have to insert mile")
    private Long mile;
    //@NotBlank(message="you have to insert caption")

    @DateTimeFormat(pattern = "MM-dd-YYYY")
    private LocalDate upload_Date;

    private String caption;
    //@NotBlank(message="you have to insert desription")
    private String description;


    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = true)
    private User user;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Bidding bidding;

    @ManyToMany
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories;


           }

