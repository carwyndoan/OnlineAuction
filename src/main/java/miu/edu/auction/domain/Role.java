package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    private String role;
}
