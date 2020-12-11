package miu.edu.auction.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InvoiceDTO {
    //Table User
    private String order_Name;
    private String order_Street;
    private String order_City;
    private String order_State;
    private String order_ZipCode;
    private String order_Email;

    //Table Payment
    private String shipping_Name;
    private String shipping_Street;
    private String shipping_City;
    private String shipping_State;
    private String shipping_ZipCode;
    private LocalDateTime shipping_Date;
    private LocalDateTime deliveredDate;
    private Double payment_DepositAmount;
    private LocalDateTime payment_DepositDate;
    private Double payment_RemainingAmount;
    private LocalDateTime payment_Date;
    private Double total;

    //Bidding, Product
    private String product_Name;
    private Double product_Price;
    private String product_VendorName;
    private String product_Description;
    private Integer product_Quantity;

    //Invoice
    private String invoice_Number;
    private LocalDate invoice_CreatedDate;
}
