package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.dto.ValidList;
import com.example.sb_assign_16_05_23.service.StudentService;
import com.example.sb_assign_16_05_23.util.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    //student controller to create endpoint /api/students

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    private ResponseDTO<List<StudentDTO>> getStudents() {
        List<StudentDTO> studentDto = studentService.getAllStudents();
        if (studentDto == null) { // check null condition for list
            return ResponseDTO.<List<StudentDTO>>builder()
                    .data(null).message(Constants.EMPTY_LIST).status(HttpStatus.NO_CONTENT.value())
                    .build();
        }

        return ResponseDTO.<List<StudentDTO>>builder()
                .data(studentDto).message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/list")
    public ResponseDTO<List<StudentDTO>> registerStudentsList(@RequestBody @Valid ValidList<StudentDTO> studentDtos) {
        List<StudentDTO> dtos = studentService.registerStudentList(studentDtos);
        return ResponseDTO.<List<StudentDTO>>builder()
                .data(dtos).message(Constants.CREATED).status(HttpStatus.CREATED.value())
                .build();
    }

    @PutMapping("/{sid}")
    private ResponseDTO<StudentDTO> updateStudent(@PathVariable("sid") Long sid, @RequestBody StudentDTO student) {
        if (!sid.equals(student.getId())) {
            return ResponseDTO.<StudentDTO>builder().data(student).message(Constants.BAD_REQUEST_MSG).status(HttpStatus.BAD_REQUEST.value()).build();
        }
        return ResponseDTO.<StudentDTO>builder().data(studentService.updateStudent(student))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }

}