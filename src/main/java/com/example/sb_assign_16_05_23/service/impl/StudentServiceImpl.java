package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ResponseDTO<?> responseDTO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseDTO<?> getAllStudents() {
        List<StudentDTO> students = studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
        System.out.println(responseDTO.getResponseDTO(students, "All Students Retrieved From Database").toString());
        return responseDTO.getResponseDTO(students, "All Students Retrieved From Database");
    }
    @Override
    public ResponseDTO sortAccordingToRank() {
        List<StudentDTO> studentListSorted =  studentRepository.findAll().stream().
                sorted(Comparator.comparing(Student::getStudentRank)).
                map(student -> modelMapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
        return responseDTO.getResponseDTO(studentListSorted,"All students sorted by rank");
    }

    @Override
    public ResponseDTO sortAccordingTo(String sortField) {
        Comparator<Student> comparator = switch (sortField) {
            case "name" -> Comparator.comparing(Student::getStudentName);
            case "marks" -> Comparator.comparing(Student::getMarks);
            case "rank" -> Comparator.comparing(Student::getStudentRank);
            case "id", default -> Comparator.comparing(Student::getId);
        };
        List<StudentDTO> studentDTOList= studentRepository.findAll().stream()
                .sorted(comparator)
                .map(student -> modelMapper.map(student,StudentDTO.class))
                .toList();
        return responseDTO.getResponseDTO(studentDTOList,"Sorted by the given field");
    }
}