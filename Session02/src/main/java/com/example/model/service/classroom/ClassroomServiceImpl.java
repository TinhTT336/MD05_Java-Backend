package com.example.model.service.classroom;

import com.example.model.dao.classroom.ClassroomDAO;
import com.example.model.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassroomServiceImpl implements ClassroomService{


    @Override
    public List<Classroom> findAll() {
        return null;
    }
}
