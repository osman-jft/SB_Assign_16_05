package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.Pair;
import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.dto.ValidList;
import com.example.sb_assign_16_05_23.service.StudentService;
import com.example.sb_assign_16_05_23.util.Constants;
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
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    private ResponseDTO<List<StudentDTO>> getStudents() {
        List<StudentDTO> studentDto = studentService.getAllStudents();

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

    @PutMapping
    private ResponseDTO<StudentDTO> updateStudent(@RequestBody @Valid StudentDTO student) {
        return ResponseDTO.<StudentDTO>builder().data(studentService.updateStudent(student))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }

    @GetMapping("/sort/rank")
    public ResponseDTO<List<StudentDTO>> getStudentsSortedByRank() {
        return ResponseDTO.<List<StudentDTO>>builder()
                .data(studentService.sortAccordingToRank()).message(Constants.SUCCESS_MSG)
                .status(HttpStatus.OK.value())
                .build();
    }

    //Fetch all students sorted by the given column name accepted.
    @GetMapping("/sort/{sortField}")
    public ResponseDTO getStudentsSortedBy(@PathVariable String sortField) {
        return ResponseDTO.<List<StudentDTO>>builder()
                .data(studentService.sortAccordingTo(sortField)).message(Constants.SUCCESS_MSG)
                .status(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/marks")
    private ResponseDTO<List<StudentDTO>> getStudentsGreaterthan(@RequestParam("value") Double value) {
        List<StudentDTO> dtos = studentService.findByMarksGreaterThan(value);
        return ResponseDTO.<List<StudentDTO>>builder().data(dtos).message(Constants.SUCCESS_MSG)
                .status(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/target")
    public ResponseDTO<List<Pair<String>>> getStudentPairs(@RequestParam Double target) {
        return ResponseDTO.<List<Pair<String>>>builder()
                .data(studentService.getStudentPairEqualsToSum(target)).message(Constants.SUCCESS_MSG)
                .status(HttpStatus.OK.value())
                .build();
    }

}

