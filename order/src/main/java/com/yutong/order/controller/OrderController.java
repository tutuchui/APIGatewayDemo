package com.yutong.order.controller;

import com.google.gson.Gson;
import com.yutong.order.common.Payment;
import com.yutong.order.common.TransactionRequest;
import com.yutong.order.common.TransactionResponse;
import com.yutong.order.entity.Order;
import com.yutong.order.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    Gson gson;

    @PostMapping("/bookOrder")
    public ResponseEntity<String> bookOrder(@RequestBody String requestBody){
        TransactionRequest transactionRequest = gson.fromJson(requestBody,TransactionRequest.class);
        TransactionResponse response = orderService.saveOrder(transactionRequest);
        return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
    }

}
