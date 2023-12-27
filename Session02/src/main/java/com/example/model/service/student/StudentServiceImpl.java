package com.example.model.service.student;

import com.example.model.dao.student.StudentDAO;
import com.example.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDAO studentDAO;
    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Boolean save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public Boolean update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentDAO.findById(id);
    }
}
