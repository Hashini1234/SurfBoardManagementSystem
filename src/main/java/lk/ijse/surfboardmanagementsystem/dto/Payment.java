package lk.ijse.surfboardmanagementsystem.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class Payment {
    private String paymentId;
    private BigDecimal amount;
    private String sessionId;
    private String paymentMethod;
    private Date paymentDate;

    public Payment() {}

    public Payment(String paymentId, BigDecimal amount, String sessionId, String paymentMethod, Date paymentDate) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.sessionId = sessionId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}