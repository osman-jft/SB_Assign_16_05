package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    /*
    6.2. Create a get api to accept a subject name and return corresponding teacher's name.
     */
    @Autowired
    SubjectService subjectService;
    @GetMapping("/list")
    private List<SubjectDTO> getAllSubjects()
    {
        return subjectService.getAllSubject();
    }
    @GetMapping("/{name}")
    private SubjectDTO getSubjectByName(@PathVariable String name){
        return subjectService.getSubjectandTeacherName(name);
    }
}
