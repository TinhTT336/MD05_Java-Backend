package com.example.controller;

import com.example.model.dto.OrderResponseDTO;
import com.example.model.entity.Orders;
import com.example.service.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        List<OrderResponseDTO> orders = orderService.findAll();
//        List<Orders> orders = orderService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrders(@PathVariable Long id) {
        Orders orders = orderService.findById(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping("/orders/confirm-order/{id}")
    public ResponseEntity<?> confirmOrder(@PathVariable Long id) {
        Orders order = orderService.confirmOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PatchMapping("/orders/cancel-order/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        Orders order = orderService.cancelOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<?>create(@RequestBody OrderResponseDTO orderResponseDTO){
        Orders orders=orderService.saveOrUpdate(orderResponseDTO);
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }
}
