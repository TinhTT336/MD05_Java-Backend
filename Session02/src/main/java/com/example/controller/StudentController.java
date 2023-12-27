package com.example.controller;

import com.example.model.entity.Classroom;
import com.example.model.entity.Student;
import com.example.model.service.classroom.ClassroomService;
import com.example.model.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/student")
    public String student(Model model){
        List<Student> studentList=studentService.findAll();
        model.addAttribute("studentList",studentList);
        System.out.println(studentList.size());
        return "student/index";
    }

    @GetMapping("/student-add")
    public String add(Model model){
        Student student=new Student();
        List<Classroom>classroomList=classroomService.findAll();
        model.addAttribute("student",student);
        model.addAttribute("classroomList",classroomList);
        return "student/add";
    }
    @PostMapping("/student-add")
    public String addPost(@ModelAttribute("student")Student student){
        if(studentService.save(student)){
            return "redirect:/student";
        }
        return "student/add";
    }
}
