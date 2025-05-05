package lk.ijse.surfboardmanagementsystem.dto;

public class BeachLocation {
    private String beachId;
    private String name;
    private String peakSeason;

    public BeachLocation() {}

    public BeachLocation(String beachId, String name, String peakSeason) {
        this.beachId = beachId;
        this.name = name;
        this.peakSeason = peakSeason;
    }

    public String getBeachId() {
        return beachId;
    }

    public void setBeachId(String beachId) {
        this.beachId = beachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeakSeason() {
        return peakSeason;
    }

    public void setPeakSeason(String peakSeason) {
        this.peakSeason = peakSeason;
    }
}