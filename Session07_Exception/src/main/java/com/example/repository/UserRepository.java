package com.example.repository;

import com.example.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByPassword(String password);

    @Modifying
    @Query("update User u set u.status=case when u.status=true then false else true end where u.id=?1")
    void changeStatus(Long id);
}
