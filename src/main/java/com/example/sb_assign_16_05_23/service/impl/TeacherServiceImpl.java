package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Qualifier("getNewModelMapper")
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<TeacherDTO> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();


        List<TeacherDTO> teacherDTOS = teachers
                .stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());

        return teacherDTOS;
    }

    @Override
    public ResponseEntity<String> setTeachers(Teacher teacherData){
        teacherRepository.save(teacherData);
        return ResponseEntity.ok("Data Saved");
    }
}