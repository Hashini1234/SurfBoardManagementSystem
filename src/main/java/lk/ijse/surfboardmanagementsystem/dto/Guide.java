package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor
@ToString
@Getter
@AllArgsConstructor
@Setter

public class Guide {
    private String guideId;
    private String name;
    private String contactDetails;
    private String experienceLevel;
    private Double payFor;
    private String status;

}