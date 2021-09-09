package com.yutong.payment.service;

import com.yutong.payment.entity.Payment;
import com.yutong.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.saveAndFlush(payment);
    }

    public String paymentProcessing(){
        return new Random().nextBoolean()?"success":"failed";
    }
}
