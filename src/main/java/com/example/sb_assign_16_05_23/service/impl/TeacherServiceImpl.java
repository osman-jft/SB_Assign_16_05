package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class
TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    ResponseDTO teacherResponse;

    @Autowired
    List<Teacher> teacherList;



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
    public ResponseDTO<TeacherDTO> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();
        teacherResponse.setData(teachers.stream()
                .map(student -> modelMapper.map(student, TeacherDTO.class))
                .collect(Collectors.toList()));
        teacherResponse.setStatus(+HttpStatus.OK.value()+" "+ HttpStatus.OK.getReasonPhrase());
        teacherResponse.setMessage("All Teachers Retrieved From Database");

        return teacherResponse;
    }

    @Override
    public ResponseDTO<TeacherDTO> setTeachers(TeacherDTO teacherData){
        Teacher teacher = teacherDTOToTeacher(teacherData);
        List<TeacherDTO> teacherDTO = Collections.singletonList(modelMapper.map(teacher, TeacherDTO.class));

        teacherResponse.setData(teacherDTO);
        teacherResponse.setStatus(+HttpStatus.OK.value()+" "+ HttpStatus.OK.getReasonPhrase());
        teacherResponse.setMessage("Teacher added to Database");
        return teacherResponse;
    }

    @Override
    public ResponseDTO<TeacherDTO> setAll(List<TeacherDTO> teacherData) {

        for(TeacherDTO teacherD: teacherData){
            teacherList.add(teacherDTOToTeacher(teacherD));
        }

        teacherResponse.setData(teacherList);
        teacherResponse.setStatus(+HttpStatus.OK.value()+" "+ HttpStatus.OK.getReasonPhrase());
        teacherResponse.setMessage("List of Teachers added to Database");
        return teacherResponse;
    }
}
