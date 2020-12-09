package miu.edu.auction.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BiddingActivityDTO {
    private Integer id;
    private String productName;
    private String userName;
    private Double biddingPrice;
    private LocalDateTime biddingDate;
}
