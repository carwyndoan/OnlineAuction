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
    private int id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @Column(columnDefinition = "integer default 1")
    private int enable;

    private String name;

    @Column(columnDefinition = "integer default 0")
    private int registration_verified;

    @Column(columnDefinition = "integer default 0")
    private int profile_verified;

    private String driver_license;

    private int user_type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bidding> biddings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Verification> verifications;
}
