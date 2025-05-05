package lk.ijse.surfboardmanagementsystem.dto;

import java.math.BigDecimal;

public class Transport {
    private String transportId;
    private String location;
    private BigDecimal cost;
    private String touristId;
    private String vehicleType;

    public Transport() {}

    public Transport(String transportId, String location, BigDecimal cost, String touristId, String vehicleType) {
        this.transportId = transportId;
        this.location = location;
        this.cost = cost;
        this.touristId = touristId;
        this.vehicleType = vehicleType;
    }

    public String getTransportId() {
        return transportId;
    }

    public void setTransportId(String transportId) {
        this.transportId = transportId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}