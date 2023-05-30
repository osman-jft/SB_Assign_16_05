package com.example.sb_assign_16_05_23.controller;
import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.service.TeacherService;
import com.example.sb_assign_16_05_23.util.Constants
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ResponseDTO<List<TeacherDTO>> getTeachers() {
        //returns list of students from StudentService
        return ResponseDTO.<List<TeacherDTO>>builder().data(teacherService.getAllTeachers())
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }

    @PostMapping
    private ResponseDTO<List<TeacherDTO>> setTeachers(@RequestBody TeacherDTO teacherData) {
        return ResponseDTO.<List<TeacherDTO>>builder().data(teacherService.setTeachers(teacherData))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }

    @PostMapping("/list")
    private ResponseDTO<List<TeacherDTO>> setAll(@RequestBody List<TeacherDTO> teacherData) {
        return ResponseDTO.<List<TeacherDTO>>builder().data(teacherService.setAll(teacherData))
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
}