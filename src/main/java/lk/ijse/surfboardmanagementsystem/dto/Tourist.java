package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Tourist {
    private String touristId;
    private String name;
    private String address;
    private String contactDetails;
    private String email;

}