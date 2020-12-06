package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private int enable;

    @NotBlank
    private String name;

    @NotNull
    private int registration_verified;

    @NotNull
    private int profile_verified;

    @NotBlank
    private String driver_license;

    @NotNull
    private int user_type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bidding> biddings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Verification> verifications;
}
