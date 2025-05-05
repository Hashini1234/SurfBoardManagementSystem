package lk.ijse.surfboardmanagementsystem.dto;

import java.math.BigDecimal;

public class Bill {
    private String billId;
    private BigDecimal amount;
    private String touristId;

    public Bill() {}

    public Bill(String billId, BigDecimal amount, String touristId) {
        this.billId = billId;
        this.amount = amount;
        this.touristId = touristId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
}