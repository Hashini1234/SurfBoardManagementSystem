package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class BeachLocation {
    private String beachId;
    private String name;
    private String peakSeason;
    private String month;

}