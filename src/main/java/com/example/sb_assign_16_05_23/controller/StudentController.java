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
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    //student controller to create endpoint /api/students

    @Autowired
    private StudentService studentService;

    @GetMapping
    private ResponseDTO<List<StudentDTO>> getStudents() {
        List<StudentDTO> studentDto = studentService.getAllStudents();
        if(studentDto == null){
            return ResponseDTO.<List<StudentDTO>>builder()
                    .data(null).message(Constants.EMPTY_LIST).status(HttpStatus.NO_CONTENT.value())
                    .build();
        }
        //returns list of students from StudentService
        return ResponseDTO.<List<StudentDTO>>builder()
                .data(studentDto).message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/list")
    public ResponseDTO<List<StudentDTO>> registerStudentsList(@RequestBody @Valid ValidList<StudentDTO> studentDtos){
        List<StudentDTO> dtos =  studentService.registerStudentList(studentDtos);

        return ResponseDTO.<List<StudentDTO>>builder()
                .data(dtos).message(Constants.CREATED).status(HttpStatus.CREATED.value())
                .build();
    }


}

