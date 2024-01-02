package com.example.service.orders;

import com.example.model.dto.OrderResponseDTO;
import com.example.model.entity.Orders;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> findAll();
    List<Orders>getAll();
    Orders saveOrUpdate(OrderResponseDTO orders);
    Orders findById(Long id);
    void delete(Long id);
    Orders confirmOrder(Long id);
    Orders cancelOrder(Long id);
}
