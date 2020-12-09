package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int user_id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int enable;

    @NotBlank
    @Size(min = 4, max = 20, message = "{Size.name.validation}")
    private String name;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    @Size(min = 2, max = 2, message = "{Size.state.validation}")
    private String state;

    @NotBlank
    private String zipcode;

    @Column(columnDefinition = "integer default 0")
    private int registration_verified;

    @Column(columnDefinition = "integer default 0")
    private int profile_verified;

    @NotBlank
    private String driver_license;

    @Column(columnDefinition = "integer default 0")
    private int user_type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Verification> verifications;

    @OneToMany(mappedBy = "winner")
    private List<Bidding> winBiddings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bidding_user")
    private List<Bidding_Activities> bidding_activities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_payment")
    private List<Payment> payments;
}
