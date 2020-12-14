package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class DMV {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String license;
}
