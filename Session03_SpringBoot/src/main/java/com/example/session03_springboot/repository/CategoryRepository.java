package com.example.session03_springboot.repository;

import com.example.session03_springboot.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Modifying
    @Query("UPDATE Category c SET c.status = CASE WHEN c.status = TRUE THEN FALSE ELSE TRUE END WHERE c.id=?1")
    void changeStatus(Long id);
}
