package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

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


    @Override
    public List<StudentDTO> registerStudentList(List<StudentDTO> studentDtos){
        List<Student> students = calculateRank(studentDtos);
        List<StudentDTO> newStudentDtos = new ArrayList<>();// return the dtos List with allocated id and rank

        students.forEach(s -> {
            if(s.getId() == null){ // if id=null then it is new student obj which is not registered yet
                studentRepository.save(s);
                newStudentDtos.add(modelMapper.map(s, StudentDTO.class));
            } else studentRepository.save(s);
        });

        return newStudentDtos;
    }


    // recalculate rank
    @Override
    public List<Student> calculateRank(List<StudentDTO> studentDtos) {
          List<Student> students = studentRepository.findAll(Sort.by("marks").descending()); // get student list sorted by marks
          students.addAll(studentDtos.stream() // add the new list
                          .map(s -> modelMapper.map(s, Student.class)) // map dto to entity class
                          .collect(Collectors.toList()));

        Collections.sort(students, (s1,s2) -> s2.getMarks().compareTo(s1.getMarks())); // add and sort the students list

        if (!students.isEmpty()) {
            double prevMarks = 0;
            int prevRank = -1;
            int i = 0;

            for (Student s : students) {
                if (i == 0) { // initial case
                    s.setStudentRank(1);
                    i++;
                } else {
                    if (prevMarks == s.getMarks()) { // check prev marks == current marks
                        s.setStudentRank( (prevRank != -1) ? prevRank : i); // for the first matching pair
                        prevRank = (prevRank != -1 ? -1 : i);
                    } else {
                        i++;
                        prevRank = i;
                        s.setStudentRank(i);
                    }
                }
                prevMarks = s.getMarks();
            }

        }

        return students;
    }
}