package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ValidList;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    //student controller to create endpoint /api/students

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping
    private ResponseDTO<?> getStudents() {
        List<StudentDTO> studentDto = studentService.getAllStudents();
        if(studentDto == null){
            return responseDTO.getResponseDTO(null, "Student's list is empty");
        }
        //returns list of students from StudentService
        return responseDTO.getResponseDTO(studentDto, "Student's list is retrieved successfully");
    }

    @PostMapping("/list")
    public ResponseDTO<?> registerStudentsList(@RequestBody @Valid ValidList<StudentDTO> studentDtos){
        List<StudentDTO> dtos =  studentService.registerStudentList(studentDtos);
        return responseDTO.getResponseDTO(dtos, "Student's list save successfully", HttpStatus.CREATED);
    }

}

