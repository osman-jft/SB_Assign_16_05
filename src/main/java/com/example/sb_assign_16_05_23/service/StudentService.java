package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import java.util.List;

public interface StudentService {

    StudentDTO updateStudent(Student studentData);

    ResponseDTO getAllStudents();
}
