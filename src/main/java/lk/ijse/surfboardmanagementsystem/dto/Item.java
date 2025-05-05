package lk.ijse.surfboardmanagementsystem.dto;

public class Item {
    private String itemId;
    private String name;
    private String type;
    private String conditions;
    private String availabilityStatus;

    public Item() {}

    public Item(String itemId, String name, String type, String conditions, String availabilityStatus) {
        this.itemId = itemId;
        this.name = name;
        this.type = type;
        this.conditions = conditions;
        this.availabilityStatus = availabilityStatus;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}