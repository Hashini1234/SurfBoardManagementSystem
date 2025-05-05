package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    private String paymentId;
    private BigDecimal amount;
    private String sessionId;
    private String paymentMethod;
    private Date paymentDate;



}