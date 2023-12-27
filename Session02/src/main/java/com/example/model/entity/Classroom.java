package com.example.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int classroomId;
    @Column(name = "name")
    private String classroomName;
    @Column(name = "status")
    private boolean classroomStatus = true;
    @OneToMany(mappedBy = "classroom")
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Classroom() {
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public boolean isClassroomStatus() {
        return classroomStatus;
    }

    public void setClassroomStatus(boolean classroomStatus) {
        this.classroomStatus = classroomStatus;
    }
}
