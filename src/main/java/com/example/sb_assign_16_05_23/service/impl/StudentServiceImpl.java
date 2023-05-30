package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ResponseDTO<?> responseDTO;

    @Override
    public ResponseDTO<?> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        System.out.println(responseDTO.getResponseDTO(students, "All Students Retrieved From Database").toString());

        return responseDTO.getResponseDTO(students, "All Students Retrieved From Database");
    }
    @Override
    public List<StudentDTO> sortAccordingToRank() {
        return studentRepository.findAll().stream().
                sorted(Comparator.comparing(Student::getStudentRank)).
                map(student -> modelMapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> sortAccordingTo(String sortField) {
        Comparator<Student> comparator = switch (sortField) {
            case "name" -> Comparator.comparing(Student::getStudentName);
            case "marks" -> Comparator.comparing(Student::getMarks);
            case "rank" -> Comparator.comparing(Student::getStudentRank);
            case "id", default -> Comparator.comparing(Student::getId);
        };
        return studentRepository.findAll().stream()
                .sorted(comparator)
                .map(student -> modelMapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
    }
}
