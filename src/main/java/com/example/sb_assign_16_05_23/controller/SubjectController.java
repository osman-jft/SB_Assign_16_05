package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.dto.SubjectListDTO;
import com.example.sb_assign_16_05_23.service.SubjectService;
import com.example.sb_assign_16_05_23.util.Constants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    @GetMapping
    ResponseDTO<List<SubjectDTO>> listOfSubjects( @Valid SubjectListDTO subjectListDTO) {

        List<SubjectDTO>subjectDTOList=subjectService.getAllSubjectsByTeacherAndFromDateAndToDate(subjectListDTO);

        return ResponseDTO.<List<SubjectDTO>>builder().data(
                        subjectDTOList)
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }



}
