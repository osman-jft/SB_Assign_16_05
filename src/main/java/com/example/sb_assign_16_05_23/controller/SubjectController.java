package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.service.SubjectService;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{name}")
    ResponseDTO<List<SubjectDTO>> listOfSubjects(@PathVariable String name){
        return ResponseDTO.<List<SubjectDTO>>builder().data(subjectService.getAllSubjects(name))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
}
