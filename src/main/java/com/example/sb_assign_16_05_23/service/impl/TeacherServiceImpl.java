package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    List<Teacher> teacherList;

    @Qualifier("mapperServiceImpl")
    @Autowired
    MapperServiceImpl mapperService;



    public Teacher teacherDTOToTeacher(TeacherDTO teacherData){

        Teacher teacher =  (Teacher) mapperService.Mapper(teacherData, Teacher.class);
        List<Subject> subjects = teacherData.getSubjects()
                .stream()
                .map(subject -> (Subject)mapperService.Mapper(subject, Subject.class))
                .collect(Collectors.toList());

        subjects.forEach(subject -> subject.setTeacher(teacher));
        teacher.setSubjects(subjects);

        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public ResponseDTO<?> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();

        return mapperService.getResponseDTO(teachers, "All Teachers Retrieved From Database");
    }

    @Override
    public ResponseDTO<?> setTeachers(TeacherDTO teacherData){

        Teacher teacher = teacherDTOToTeacher(teacherData);
        List<TeacherDTO> teacherDTO = Collections.singletonList((TeacherDTO) mapperService.Mapper(teacher, TeacherDTO.class));

        return mapperService.getResponseDTO(teacherDTO, "Teacher added to Database");
    }

    @Override
    public ResponseDTO<?> setAll(List<TeacherDTO> teacherData) {

        for(TeacherDTO teacherD: teacherData){
            teacherList.add(teacherDTOToTeacher(teacherD));
        }
        return mapperService.getResponseDTO(teacherList, "List of Teachers added to Database");

    }
}
