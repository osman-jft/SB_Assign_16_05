package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.service.SubjectService;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    /*
    6.2. Create a get api to accept a subject name and return corresponding teacher's name.
     */
    @Autowired
    SubjectService subjectService;
    @GetMapping
    private ResponseDTO<List<SubjectDTO>>  getAllSubjects()
    {
        return ResponseDTO.<List<SubjectDTO>>builder().data(subjectService.getAllSubject())
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
    @GetMapping("/{name}")
    private ResponseDTO<List<SubjectDTO>>  getSubjectByName(@PathVariable String name){
        return ResponseDTO.<List<SubjectDTO>>builder().data(subjectService.getSubjectandTeacherName(name))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
}
