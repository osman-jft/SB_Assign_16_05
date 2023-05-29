package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ResponseDTO responseDTO;

    @Autowired
    ModelMapper modelMapper;



    @Override
    //create a List of Student type & store all db entries into it
    public ResponseDTO getAllStudents () {
        List<Student> students = studentRepository.findAll();
        System.out.println(responseDTO.getResponseDTO(students, "All Students Retrieved From Database").toString());
        return responseDTO.getResponseDTO(students, "All Students Retrieved From Database");
    }

    @Override
    public StudentDTO updateStudent(Student studentData) {
        Student existingStudent = studentRepository.findById(studentData.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setStudentName(studentData.getStudentName());
        existingStudent.setMarks(studentData.getMarks());

        List<Student> allStudents = studentRepository.findAll();
        Collections.sort(allStudents, (s1, s2) -> {
            return (int) (s2.getMarks() - s1.getMarks());
        });
        int rank = 1;
        double previousMarks = Double.POSITIVE_INFINITY;

        for (Student student : allStudents) {
            if (student.getMarks() < previousMarks) {
                student.setRank(rank);
                rank++;
            } else {
                student.setRank(rank - 1);
            }
            previousMarks = student.getMarks();
            studentRepository.save(student);
        }

        // Updating the student
        studentRepository.save(existingStudent);
        TypeToken<StudentDTO> typeToken = new TypeToken<>() {
        };
        // Casting student class to StudentDTO
        return modelMapper.map(existingStudent, typeToken.getType());
    }


}

