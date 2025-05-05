package lk.ijse.surfboardmanagementsystem.dto;

public class SurfBoard {
    private String surfboardId;
    private String brand;
    private String conditions;

    public SurfBoard() {}

    public SurfBoard(String surfboardId, String brand, String conditions) {
        this.surfboardId = surfboardId;
        this.brand = brand;
        this.conditions = conditions;
    }

    public String getSurfboardId() {
        return surfboardId;
    }

    public void setSurfboardId(String surfboardId) {
        this.surfboardId = surfboardId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}