package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.service.SubjectService;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/subject")
    private ResponseDTO<List<SubjectDTO>> getSubjectByName(@RequestParam(value = "name") String name) {
            List<SubjectDTO> subjectDTOS = subjectService.getSubjectandTeacherName(name);
            return ResponseDTO.<List<SubjectDTO>>builder()
                    .data(subjectDTOS)
                    .message(Constants.SUCCESS_MSG)
                    .status(HttpStatus.OK.value()).build();
    }

}
