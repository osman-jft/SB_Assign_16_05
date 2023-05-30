package com.example.sb_assign_16_05_23.service.impl;


import com.example.sb_assign_16_05_23.dto.ResponseDTO;

import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class
TeacherServiceImpl implements TeacherService {


    private final TeacherRepository teacherRepository;


    private final ModelMapper modelMapper;


    private final ResponseDTO responseDTO;

    public TeacherServiceImpl(TeacherRepository teacherRepository, ModelMapper modelMapper, ResponseDTO responseDTO) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
        this.responseDTO = responseDTO;
    }


    public Teacher teacherDTOToTeacher(TeacherDTO teacherData){

        Teacher teacher =  modelMapper.map(teacherData, Teacher.class);
        List<Subject> subjects = teacherData.getSubjects()
                .stream()
                .map(subject -> modelMapper.map(subject, Subject.class))
                .collect(Collectors.toList());

        subjects.forEach(subject -> subject.setTeacher(teacher));
        teacher.setSubjects(subjects);

        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public ResponseDTO getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();

        return responseDTO.getResponseDTO(teachers, "All Teachers Retrieved From Database");
    }

    @Override
    public ResponseDTO setTeachers(TeacherDTO teacherData){

        Teacher teacher = teacherDTOToTeacher(teacherData);
        List<TeacherDTO> teacherDTO = Collections.singletonList(modelMapper.map(teacher, TeacherDTO.class));

        return responseDTO.getResponseDTO(teacherDTO, "Teacher added to Database");
    }

    @Override
    public ResponseDTO setAll(List<TeacherDTO> teacherData) {

//        List<Teacher> teacherList = new ArrayList<>();

        List<Teacher> teacherList = teacherData.stream().map(this::teacherDTOToTeacher).collect(Collectors.toList());
        return responseDTO.getResponseDTO(teacherList, "List of Teachers added to Database");

    }


}
