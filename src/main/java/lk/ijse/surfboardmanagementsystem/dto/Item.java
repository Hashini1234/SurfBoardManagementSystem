package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
@ToString
public class Item {
    private String itemId;
    private String name;
    private String type;
    private String conditions;
    private String availabilityStatus;

}