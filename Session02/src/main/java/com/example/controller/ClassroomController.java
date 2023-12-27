package com.example.controller;

import com.example.model.entity.Classroom;
import com.example.model.service.classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;
    @GetMapping("/")
    public String index(Model model){
        List<Classroom> classroomList=classroomService.findAll();
        model.addAttribute("classroomList",classroomList);
        System.out.println(classroomList);
        return "classroom/index";
    }
}
