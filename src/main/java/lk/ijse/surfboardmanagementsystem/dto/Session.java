package lk.ijse.surfboardmanagementsystem.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Time;
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Session {
    private String sessionId;
    private Date date;
    private Time time;
    private String duration;
    private String touristId;
    private String beachId;
    private String guideId;
    private String status;


}