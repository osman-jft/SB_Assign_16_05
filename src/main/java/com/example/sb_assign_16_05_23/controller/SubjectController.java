package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.service.SubjectService;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")

public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping
    private ResponseDTO<List<SubjectDTO>> getAllSubjects() {
        return ResponseDTO.<List<SubjectDTO>>builder().data(subjectService.getAllSubject())
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value())
                .build();
    }


    @GetMapping("/name")
    private ResponseDTO<List<SubjectDTO>> getSubjectByName(@RequestParam(value = "subject_name") String subject_name/*@PathVariable String name*/) {
        List<SubjectDTO> subjectDTOList = subjectService.getSubjectandTeacherName(subject_name);
        if (subjectDTOList == null)
            return ResponseDTO.<List<SubjectDTO>>builder().data(null).message(Constants.EMPTY_LIST)
                    .status(HttpStatus.NO_CONTENT.value())
                    .build();
        else
            return ResponseDTO.<List<SubjectDTO>>builder().data(subjectDTOList)
                    .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value())
                    .build();
    }


}
