package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //student controller to create endpoint /api/students

    @Autowired
    StudentService studentService;

    @GetMapping
    private List<StudentDTO> getStudents() {

        //returns list of students from StudentService
        return studentService.getAllStudents();
    }
    @GetMapping("/students/greaterthan/{value}")
    private List<StudentDTO> getStudentsGreaterThan(@PathVariable double value){
        return  studentService.getStudentsGreaterThan(value);
    }
}
