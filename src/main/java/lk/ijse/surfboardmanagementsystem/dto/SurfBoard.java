package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class SurfBoard {
    private String surfboardId;
    private String brand;
    private String conditions;
    private String status; // Available, Not Available, Under Maintenance

}