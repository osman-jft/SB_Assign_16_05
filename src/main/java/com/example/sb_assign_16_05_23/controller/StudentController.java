package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.hibernate.mapping.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //student controller to create endpoint /api/students


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/list")
    private ResponseDTO getStudents() {


        //returns list of students from StudentService
        return studentService.getAllStudents();
    }
    @GetMapping("/greater/{value}")
    private ResponseDTO getStudentsGreaterThan(@PathVariable Double value){
        return  studentService.getStudentsGreaterThan(value);
    }
}
