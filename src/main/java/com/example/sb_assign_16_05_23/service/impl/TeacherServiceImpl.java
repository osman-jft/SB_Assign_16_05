package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.TeacherDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

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
    public ResponseEntity<String> setTeachers(TeacherDTO teacherData){

        Teacher teacher =  modelMapper.map(teacherData, Teacher.class);
        List<Subject> subjects = teacherData.getSubjects()
                .stream()
                .map(subject -> modelMapper.map(subject, Subject.class))
                        .collect(Collectors.toList());

        subjects.forEach(subject -> subject.setTeacher(teacher));
        teacher.setSubjects(subjects);
        teacherRepository.save(teacher);

        return ResponseEntity.ok("Data Saved");
    }

    @Override
    public ResponseEntity<String> setAll(List<TeacherDTO> teacherData) {
        for(TeacherDTO teacher: teacherData){
            setTeachers(teacher);
        }
        return ResponseEntity.ok("Data Saved");
    }
}
