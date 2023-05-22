package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ResponseEntity<?> getStudents() {
        List<StudentDTO> studentDto = studentService.getAllStudents();
        if(studentDto == null){
            ResponseEntity.accepted().body("DB is empty");
        }
        //returns list of students from StudentService
        return ResponseEntity.accepted().body(studentDto);
    }


    @PostMapping("/students")
    public ResponseEntity<?> registerStudents(@Valid @RequestBody StudentDTO studentDto){
        if(studentDto != null){
            StudentDTO sDto = studentService.registerStudent(studentDto);
            return ResponseEntity.accepted().body(sDto);
        }


        return ResponseEntity.accepted().body("Failed to register student");

    }

    @PostMapping("/studentslist")
    public ResponseEntity<?> registerStudentsList(@RequestBody @Valid List<StudentDTO> studentDTOS){
        if(studentDTOS != null){
            for(StudentDTO s : studentDTOS){
                StudentDTO sDto = studentService.registerStudent(s);
            }

            return ResponseEntity.accepted().body(studentDTOS);
        }
        return ResponseEntity.accepted().body("Students List does not register to DB");
    }
}
