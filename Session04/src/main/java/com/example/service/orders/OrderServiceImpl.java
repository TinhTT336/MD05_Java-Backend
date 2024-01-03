package com.example.service.orders;

import com.example.model.dto.OrderResponseDTO;
import com.example.model.entity.Orders;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderResponseDTO> findAll() {
        List<Orders> list = orderRepository.findAll();
        List<OrderResponseDTO> orders = new ArrayList<>();
        for (Orders ord : list) {
            OrderResponseDTO order = new OrderResponseDTO();
            order.setId(ord.getId());
            order.setAddress(ord.getAddress());
            order.setPhone(ord.getPhone());
            order.setStatus(ord.getStatus());
            order.setTotalPrice(ord.getTotalPrice());
            order.setUserId(ord.getUser().getId());
            orders.add(order);
        }
        return orders;
    }

    @Override
    public List<Orders> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders saveOrUpdate(OrderResponseDTO orderResponseDTO) {
        Orders order = new Orders();
        order.setId(orderResponseDTO.getId());

        Long userId = orderResponseDTO.getUserId(); // Assuming there is a user ID in the DTO
        if (userId != null) {
            order.setUser(userRepository.findById(userId).orElse(null));
        }
        order.setTotalPrice(orderResponseDTO.getTotalPrice());
        order.setStatus(orderResponseDTO.getStatus());
        order.setPhone(orderResponseDTO.getPhone());
        order.setAddress(orderResponseDTO.getAddress());
        return orderRepository.save(order);
    }

    @Override
    public Orders findById(Long id) {

        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Orders confirmOrder(Long id) {
        orderRepository.confirmOrder(id);
        return findById(id);
    }

    @Override
    public Orders cancelOrder(Long id) {
        orderRepository.cancelOrder(id);
        return findById(id);
    }


}
