package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;

public interface StudentService {

    ResponseDTO<StudentDTO> getAllStudents();
}
