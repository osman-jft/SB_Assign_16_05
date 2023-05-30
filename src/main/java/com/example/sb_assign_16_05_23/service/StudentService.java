package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    ResponseDTO<?> getAllStudents();
    ResponseDTO sortAccordingToRank();
    ResponseDTO sortAccordingTo(String sortField);
    }
