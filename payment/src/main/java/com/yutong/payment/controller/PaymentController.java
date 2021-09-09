package com.yutong.payment.controller;

import com.google.gson.Gson;
import com.yutong.payment.entity.Payment;
import com.yutong.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    Gson gson;

    @PostMapping("/doPayment")
    public String doPayment(@RequestBody String requestBody){
        Payment payment = gson.fromJson(requestBody,Payment.class);
        return gson.toJson(paymentService.doPayment(payment));
    }
}
