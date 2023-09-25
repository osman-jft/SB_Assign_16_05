package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.Pair;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    List<Student> calculateRank(List<Student> studentDto);

    List<StudentDTO> registerStudentList(List<StudentDTO> studentDtos);

    StudentDTO updateStudent(StudentDTO studentDTO);

    List<StudentDTO> sortAccordingToRank();

    List<StudentDTO> sortAccordingTo(String sortField);

    List<StudentDTO> findByMarksGreaterThan(Double value);

    List<Pair<String>> getStudentPairEqualsToSum(Double target);

}

