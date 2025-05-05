package lk.ijse.surfboardmanagementsystem.dto;

import java.sql.Date;
import java.sql.Time;

public class Session {
    private String sessionId;
    private Date date;
    private Time time;
    private String duration;
    private String touristId;
    private String beachId;
    private String guideId;
    private String status;

    public Session() {}

    public Session(String sessionId, Date date, Time time, String duration,
                   String touristId, String beachId, String guideId, String status) {
        this.sessionId = sessionId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.touristId = touristId;
        this.beachId = beachId;
        this.guideId = guideId;
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getBeachId() {
        return beachId;
    }

    public void setBeachId(String beachId) {
        this.beachId = beachId;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}