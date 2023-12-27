package com.example.model.dao.student;

import com.example.model.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> findAll();
    Boolean save(Student student);
    Boolean update(Student student);
    Student findById(Integer id);
}
