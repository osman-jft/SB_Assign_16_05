package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.service.SubjectService;
import com.example.sb_assign_16_05_23.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    @GetMapping("/")
    ResponseDTO<List<SubjectDTO>> listOfSubjects(@RequestParam(value = "teacherName",defaultValue="") String name,
                                                 @RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo ){
        Integer noOfRecords=5;
        return ResponseDTO.<List<SubjectDTO>>builder().data(subjectService.getAllSubjects(name,pageNo,noOfRecords))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
}
