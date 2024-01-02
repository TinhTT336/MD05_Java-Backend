package com.example.repository;

import com.example.model.dto.OrderResponseDTO;
import com.example.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Modifying
    @Query("update Orders o set o.status=1 where o.id=?1")
    void confirmOrder(Long id);

    @Modifying
    @Query("update Orders o set o.status=2 where o.id=?1")
    void cancelOrder(Long id);
}
