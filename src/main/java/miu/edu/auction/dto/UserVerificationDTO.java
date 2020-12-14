package miu.edu.auction.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UserVerificationDTO {

    private String name;
    private String licenseNumber;
    private String isVerfied;

}
