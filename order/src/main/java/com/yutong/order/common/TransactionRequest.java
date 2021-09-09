package com.yutong.order.common;

import com.yutong.order.entity.Order;

public class TransactionRequest {
    private Payment payment;
    private Order order;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
