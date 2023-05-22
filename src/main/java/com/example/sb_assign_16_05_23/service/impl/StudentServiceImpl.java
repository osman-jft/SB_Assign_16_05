package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
        if(students.isEmpty()){
            return null;
        }
        List<StudentDTO> studentDTOS = students
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());

        return studentDTOS;

        }

        // save student to db
        @Override
    public StudentDTO registerStudent(@Valid StudentDTO sDto) {
        Student students = new Student();

        students.setStudentName(sDto.getName());
        students.setMarks(sDto.getMarks());
        students.setStudentsRank(sDto.getRanks());
        studentRepository.save(students);
        // recalculate the rank
        calculateRank();

        sDto.setRanks(students.getStudentsRank());
        sDto.setId(students.getId());
        return sDto;
    }


    // recalculate rank
    @Override
    public void calculateRank() {
        List<Student> students = studentRepository.findAllByMarks();
        System.out.println(students);

        if (!students.isEmpty()) {
            double prevMarks = 0;
            int prevRank = -1;
            int i = 0;

            for (Student s : students) {
                if (i == 0) { // initial case
                    s.setStudentsRank(1);
                    i++;
                } else {
                    if (prevMarks == s.getMarks()) { // check prev marks == current marks
                        // for the first matching pair
                        if (prevRank != -1) {
                            s.setStudentsRank(prevRank);
                        } else {
                            prevRank = i;
                            s.setStudentsRank(i);
                        }
                    } else {
                        i++;
                        prevRank = i;
                        s.setStudentsRank(i);
                    }
                }
                prevMarks = s.getMarks();
                studentRepository.save(s);
            }
        }

    }
}