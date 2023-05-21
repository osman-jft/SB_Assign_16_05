package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    //student controller to create endpoint /api/students

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    private List<StudentDTO> getStudents() {

        //returns list of students from StudentService
        return studentService.getAllStudents();
    }

    @PutMapping("/students/{sid}")
    private ResponseEntity<StudentDTO> updateStudent(@PathVariable Long sid, @RequestBody Student student) {
        if (!sid.equals(student.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(studentService.updateStudent(student), HttpStatus.ACCEPTED);
    }

}
