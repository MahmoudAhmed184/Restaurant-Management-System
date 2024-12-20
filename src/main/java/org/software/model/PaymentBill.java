package org.software.model;

import java.math.BigDecimal;

public class PaymentBill {

    private int paymentId;
    private int orderId;
    private String paymentMethod;
    private BigDecimal totalPrice;
    private String status;
    private String paymentDate;


    public PaymentBill(int paymentId, int orderId, String paymentMethod, BigDecimal totalPrice, String status, String paymentDate) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentDate = paymentDate;
    }



    public int getPaymentId() {
        return paymentId;
    }


    public int getOrderId() {
        return orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getTotalPrice() {

        return totalPrice;
    }

    public String getStatus() {

        return status;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

}
