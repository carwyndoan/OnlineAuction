package miu.edu.auction.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int verification_id;

    private String code;

    private LocalDateTime generated_time;

  //  @NotNull
    private int type; //0: generated uniqueID, 1: User verification

    private LocalDateTime verifying_time;

 //   @NotNull
    @Column(columnDefinition = "integer default 0")
    private int status;

    private String description;
     int trial;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
