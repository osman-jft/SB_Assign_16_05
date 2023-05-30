package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
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

    @Autowired
    StudentService studentService;

    @GetMapping
    private ResponseDTO<?> getStudents() {
        return studentService.getAllStudents();
    }

    //Fetch all students sorted by rank.
    @GetMapping("/students-sorted-by-rank")
    public ResponseDTO getStudentsSortedByRank() {
        return studentService.sortAccordingToRank();
    }

    //Fetch all students sorted by the given column name accepted.
    @GetMapping("/students-sort-by-{sortField}")
    public ResponseDTO getStudentsSortedBy(@PathVariable String sortField) {
        return studentService.sortAccordingTo(sortField);
    }
}
