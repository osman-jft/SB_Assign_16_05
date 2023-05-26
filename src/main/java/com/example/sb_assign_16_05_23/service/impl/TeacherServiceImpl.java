package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class
TeacherServiceImpl extends MapperServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    List<Teacher> teacherList;



    public Teacher teacherDTOToTeacher(TeacherDTO teacherData){

        Teacher teacher =  (Teacher)super.Mapper(teacherData, Teacher.class);
        List<Subject> subjects = teacherData.getSubjects()
                .stream()
                .map(subject -> (Subject)super.Mapper(subject, Subject.class))
                .collect(Collectors.toList());

        subjects.forEach(subject -> subject.setTeacher(teacher));
        teacher.setSubjects(subjects);

        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public ResponseDTO<TeacherDTO> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();

        return getResponseDTO(teachers, "All Teachers Retrieved From Database");
    }

    @Override
    public ResponseDTO<TeacherDTO> setTeachers(TeacherDTO teacherData){

        Teacher teacher = teacherDTOToTeacher(teacherData);
        List<TeacherDTO> teacherDTO = Collections.singletonList((TeacherDTO) super.Mapper(teacher, TeacherDTO.class));

        return getResponseDTO(teacherDTO, "Teacher added to Database");
    }

    @Override
    public ResponseDTO<TeacherDTO> setAll(List<TeacherDTO> teacherData) {

        for(TeacherDTO teacherD: teacherData){
            teacherList.add(teacherDTOToTeacher(teacherD));
        }
        return getResponseDTO(teacherList, "List of Teachers added to Database");

    }
}
