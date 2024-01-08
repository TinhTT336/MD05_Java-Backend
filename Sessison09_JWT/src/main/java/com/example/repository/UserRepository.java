package com.example.repository;

import com.example.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Modifying
    @Query(" update User u Set u.status=case when u.status=true then false else true end where u.id=?1")
    void changeStatus(Long id);
}
