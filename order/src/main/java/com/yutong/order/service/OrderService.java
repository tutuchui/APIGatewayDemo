package com.yutong.order.service;

import com.google.gson.Gson;
import com.yutong.order.common.Payment;
import com.yutong.order.common.TransactionRequest;
import com.yutong.order.common.TransactionResponse;
import com.yutong.order.entity.Order;
import com.yutong.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Gson gson;

    public TransactionResponse saveOrder(TransactionRequest request){
        String transactionResponseMessage = "";
        Payment payment = request.getPayment();
        Order order = request.getOrder();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        String response = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment",gson.toJson(payment),String.class);
        Payment responsePayment = gson.fromJson(response, Payment.class);
        if(responsePayment.getPaymentStatus().equals("success")){
            transactionResponseMessage = "payment processing is successful";
        }else{
            transactionResponseMessage = "payment processing failure";
        }
        TransactionResponse transactionResponse = new TransactionResponse();
        orderRepository.saveAndFlush(order);
        transactionResponse.setOrder(order);
        transactionResponse.setAmount(payment.getAmount());
        transactionResponse.setMessage(transactionResponseMessage);
        transactionResponse.setTransactionId(payment.getTransactionId());
        return transactionResponse;
    }
}
