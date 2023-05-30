//package com.example.sb_assign_16_05_23.service.impl;
//
//import com.example.sb_assign_16_05_23.dto.ResponseDTO;
//import com.example.sb_assign_16_05_23.dto.StudentDTO;
//import com.example.sb_assign_16_05_23.dto.TeacherDTO;
//import com.example.sb_assign_16_05_23.entity.Student;
//import com.example.sb_assign_16_05_23.entity.Subject;
//import com.example.sb_assign_16_05_23.entity.Teacher;
//import com.example.sb_assign_16_05_23.repository.TeacherRepository;
//import com.example.sb_assign_16_05_23.service.TeacherService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class
//TeacherServiceImpl implements TeacherService {
//
//    @Autowired
//    TeacherRepository teacherRepository;
//
//    @Autowired
//    ModelMapper modelMapper;
//
//    @Autowired
//    ResponseDTO<?> responseDTO;
//
//
//
//    public Teacher teacherDTOToTeacher(TeacherDTO teacherData){
//
//        Teacher teacher =  modelMapper.map(teacherData, Teacher.class);
//        List<Subject> subjects = teacherData.getSubjects()
//                .stream()
//                .map(subject -> modelMapper.map(subject, Subject.class))
//                .collect(Collectors.toList());
//
//        subjects.forEach(subject -> subject.setTeacher(teacher));
//        teacher.setSubjects(subjects);
//
//        teacherRepository.save(teacher);
//        return teacher;
//    }
//
//    @Override
//    public List<TeacherDTO> getAllTeachers() {
//        List<Teacher> teachers = teacherRepository.findAll();
//        return teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).collect(Collectors.toList());
//    }
//
//    @Override
//    public ResponseDTO<TeacherDTO> setTeachers(TeacherDTO teacherData){
//
//        Teacher teachers = teacherDTOToTeacher(teacherData);
//        List<TeacherDTO> teacherDTO = Collections.singletonList(mapper.map(teacher, TeacherDTO.class));
//
//        return teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).collect(Collectors.toList());
//    }
//
//    @Override
//    public ResponseDTO<?> setAll(List<TeacherDTO> teacherData) {
//
//        List<Teacher> teacherList = new ArrayList<>();
//
//        for(TeacherDTO teacherD: teacherData){
//            teacherList.add(teacherDTOToTeacher(teacherD));
//        }
//        return responseDTO.getResponseDTO(teacherList, "List of Teachers added to Database");
//    }
//}
