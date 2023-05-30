package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ValidList;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.service.StudentService;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    //student controller to create endpoint /api/students

    @Autowired
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    private ResponseDTO<List<StudentDTO>> getStudents() {
        List<StudentDTO> studentDto = studentService.getAllStudents();
        if(studentDto == null){ // check null condition for list
            return ResponseDTO.<List<StudentDTO>>builder()
                    .data(null).message(Constants.EMPTY_LIST).status(HttpStatus.NO_CONTENT.value())
                    .build();
        }

        return ResponseDTO.<List<StudentDTO>>builder()
                .data(studentDto).message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/list")
    public ResponseDTO<List<StudentDTO>> registerStudentsList(@RequestBody @Valid ValidList<StudentDTO> studentDtos){
        List<StudentDTO> dtos =  studentService.registerStudentList(studentDtos);



        //returns list of students from StudentService
        return ResponseDTO.<List<StudentDTO>>builder().data(studentService.getAllStudents())
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
    @GetMapping("/marks/{value}")
    private ResponseDTO<List<StudentDTO>> getStudentsGreaterthan(@PathVariable Double value) {


        //returns list of students from StudentService
        return ResponseDTO.<List<StudentDTO>>builder().data(studentService.findByMarksGreaterThan(value))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }

        return ResponseDTO.<List<StudentDTO>>builder()
                .data(dtos).message(Constants.CREATED).status(HttpStatus.CREATED.value())
                .build();
    }


}

