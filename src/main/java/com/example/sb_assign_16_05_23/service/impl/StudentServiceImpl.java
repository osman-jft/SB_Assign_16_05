package com.example.sb_assign_16_05_23.service.impl;

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
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;


    @Qualifier("getModelMapper")
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        //create a List of Student type & store all db entries into it
        List<Student> students = studentRepository.findAll();

        //using ModelMapper we map each individual member of the list of students to the DTO & cache it to be used
        //by the different layers in the application
        //we are using Java 8 ft stream()
        List<StudentDTO> studentDTOS = students
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());

        //returning the StudentDTO list to StudentController
        return studentDTOS;

        //create impl class also
    }


    public StudentDTO updateStudent(Student updatedStudent) {
        // checking student if present into DB or not
        Student existingStudent = studentRepository.findById(updatedStudent.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setStudentName(updatedStudent.getStudentName());
        existingStudent.setMarks(updatedStudent.getMarks());

        // Recalculate rank based on marks
        List<Student> allStudents = studentRepository.findAll();
        Collections.sort(allStudents, (s1, s2) -> (int) (s2.getMarks() - s1.getMarks()));

        for (int i = 0; i < allStudents.size(); i++) {
            Student student = allStudents.get(i);
            student.setRank(i + 1);
            studentRepository.save(student);
        }

        //updating the student
        studentRepository.save(existingStudent);
        TypeToken<StudentDTO> typeToken = new TypeToken<>() {
        };

        // Casting student class to StudentDTO
        return modelMapper.map(existingStudent, typeToken.getType());
    }
}