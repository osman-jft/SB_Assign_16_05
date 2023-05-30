package com.example.sb_assign_16_05_23.controller;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    //teacher controller to create endpoint /api/teachers


    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping
    private ResponseDTO getTeachers() {

        //returns list of teachers from TeacherService
        return teacherService.getAllTeachers();
    }

    @PostMapping
    private ResponseDTO setTeachers(@RequestBody TeacherDTO teacherData) {
        return teacherService.setTeachers(teacherData);
    }

    @PostMapping("/list")
    private ResponseDTO setAll(@RequestBody List<TeacherDTO> teacherData) {

        return teacherService.setAll(teacherData);
    }
}