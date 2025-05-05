package lk.ijse.surfboardmanagementsystem.dto;

public class Tourist {
    private String touristId;
    private String name;
    private String address;
    private String contactDetails;

    public Tourist() {}

    public Tourist(String touristId, String name, String address, String contactDetails) {
        this.touristId = touristId;
        this.name = name;
        this.address = address;
        this.contactDetails = contactDetails;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}