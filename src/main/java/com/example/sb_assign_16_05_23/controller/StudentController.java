package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //student controller to create endpoint /api/students

    @Autowired
    StudentService studentService;

    @GetMapping
    private ResponseEntity<?> getStudents() {
        List<StudentDTO> studentDto = studentService.getAllStudents();
        if(studentDto == null){
            return ResponseEntity.accepted().body("Students list is empty");
        }
        //returns list of students from StudentService
        return ResponseEntity.accepted().body(studentDto);
    }


    @PostMapping("/list")
    public ResponseEntity<?> registerStudentsList(@RequestBody @Valid List<StudentDTO> studentDtos){
        if(studentDtos != null){
            List<StudentDTO> dtos =  studentService.registerStudentList(studentDtos);

            return ResponseEntity.accepted().body(dtos);
        }
        return ResponseEntity.accepted().body("Students List does not register to DB");
    }
}
