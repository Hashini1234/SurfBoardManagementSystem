package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;


import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Bill {
    private String billId;
    private BigDecimal amount;
    private String touristId;

   }