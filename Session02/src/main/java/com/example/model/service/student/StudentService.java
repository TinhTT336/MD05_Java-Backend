package com.example.model.service.student;

import com.example.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Boolean save(Student student);
    Boolean update(Student student);
    Student findById(Integer id);
}
