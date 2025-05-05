package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Transport {
    private String transportId;
    private String location;
    private BigDecimal cost;
    private String touristId;
    private String vehicleType;

}