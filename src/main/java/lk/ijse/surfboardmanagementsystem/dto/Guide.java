package lk.ijse.surfboardmanagementsystem.dto;

import java.math.BigDecimal;

public class Guide {
    private String guideId;
    private String name;
    private String contactDetails;
    private String experienceLevel;
    private BigDecimal payFor;

    public Guide() {}

    public Guide(String guideId, String name, String contactDetails, String experienceLevel, BigDecimal payFor) {
        this.guideId = guideId;
        this.name = name;
        this.contactDetails = contactDetails;
        this.experienceLevel = experienceLevel;
        this.payFor = payFor;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public BigDecimal getPayFor() {
        return payFor;
    }

    public void setPayFor(BigDecimal payFor) {
        this.payFor = payFor;
    }
}