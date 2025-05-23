package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Transport {
    private String transportId;
    private String location;
    private String cost;
    private String touristId;
    private String vehicleType;

}