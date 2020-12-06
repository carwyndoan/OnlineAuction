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
    private int ID;

    private int code;

    private LocalDateTime generated_time;

    @NotNull
    private int type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
