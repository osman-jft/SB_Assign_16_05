package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.errors.NotFoundException;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    List<TeacherDTO> teacherDTO;

    public TeacherServiceImpl(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public Teacher teacherDTOToTeacher(TeacherDTO teacherData){

        Teacher teacher = modelMapper.map(teacherData, Teacher.class);
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
    public List<TeacherDTO> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();
        if (teachers.isEmpty()) throw new NotFoundException("Teacher list is empty");

        return teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<TeacherDTO> setTeachers(TeacherDTO teacherData) {
        Teacher teacher = teacherDTOToTeacher(teacherData);
        teacherDTO = Collections.singletonList(modelMapper.map(teacher, TeacherDTO.class));
        return teacherDTO;
    }

    @Override
    public List<TeacherDTO> setAll(List<TeacherDTO> teacherData) {

        List<Teacher> teacherList = teacherData.stream().map(this::teacherDTOToTeacher).toList();

        return teacherList.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).toList();

    }
}
