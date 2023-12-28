package com.example.model.dao.student;

import com.example.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDAO extends JpaRepository<Student,Integer> {
//    List<Student> findAll();
//    Boolean save(Student student);
//    Boolean update(Student student);
//    Student findById(Integer id);
}
