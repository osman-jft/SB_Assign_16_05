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
        // return the dtos List with allocated id and rank
        List<StudentDTO> newStudentDtos = new ArrayList<>();

        for(Student s : students){
            // if id=null then it is new student obj which is not registered yet
            if(s.getId() == null){
                studentRepository.save(s);
                StudentDTO dto = new StudentDTO();
                BeanUtils.copyProperties(s, dto); // copy the allocated id and rank to dto
                newStudentDtos.add(dto);
            } else {
                studentRepository.save(s);
            }
        }
        return newStudentDtos;
    }


    // recalculate rank
    @Override
    public List<Student> calculateRank(List<StudentDTO> studentDtos) {
        // get student list sorted by marks
          List<Student> students = studentRepository.findAll(Sort.by("marks").descending());

          students.addAll(
                  studentDtos.stream().map(s -> {
                      Student newS = new Student();
                      BeanUtils.copyProperties(s, newS);
                      return newS;
                  }).collect(Collectors.toList())
          );
          // add and sort the students list
        Collections.sort(students, (s1,s2) -> s2.getMarks().compareTo(s1.getMarks()));

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
                        // for the first matching pair
                        if (prevRank != -1) {
                            s.setStudentRank(prevRank);
                        } else {
                            prevRank = i;
                            s.setStudentRank(i);
                        }
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