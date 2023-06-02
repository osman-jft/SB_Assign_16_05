package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.errors.NotFoundException;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import com.example.sb_assign_16_05_23.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return null;
        }
        return students.stream().map(student -> mapper.map(student, StudentDTO.class)).collect(Collectors.toList());


    }
  
  
    @Override
    public List<StudentDTO> registerStudentList(List<StudentDTO> studentDtos) {
        List<Student> students = calculateRank(studentDtos);
        List<StudentDTO> newStudentDtos = new ArrayList<>();// return the dtos List with allocated id and rank

        students.forEach(s -> {
            if (s.getId() == null) { // if id=null then it is new student obj which is not registered yet
                studentRepository.save(s);
                newStudentDtos.add(mapper.map(s, StudentDTO.class));
            } else studentRepository.save(s);
        });

        return newStudentDtos;
    }

    // recalculate rank
    @Override
    public List<Student> calculateRank(List<StudentDTO> studentDtos) {
        List<Student> students = studentRepository.findAll(); // get student list sorted by marks
        students.addAll(studentDtos.stream() // add the new list
                .map(s -> mapper.map(s, Student.class)) // map dto to entity class
                .collect(Collectors.toList()));

        Collections.sort(students, (s1, s2) -> s2.getMarks().compareTo(s1.getMarks())); // add and sort the students list

        Map<Double, Integer> mappingList = new HashMap<>();
        int j = 1;

        for (Student student : students) { // set the map
            double marks = student.getMarks();
            if (!mappingList.containsKey(marks)) mappingList.put(marks, j++);
            else mappingList.put(marks, mappingList.get(marks));
        }

        students.forEach(student -> { // set ranks
            double marks = student.getMarks();
            if (mappingList.containsKey(marks)) student.setStudentRank(mappingList.get(marks));
        });
        return students;

    }

    
     @Override
    public List<StudentDTO> findByMarksGreaterThan(Double value){
        List<StudentDTO> students = studentRepository.findByMarksGreaterThan(value).stream()
                .map(student -> mapper.map(student, StudentDTO.class))
            
                .toList();
        return students;
    }


}


    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {

        Student existingStudent = studentRepository.findById(studentDTO.getId())
                .orElseThrow(() -> new NotFoundException("Student not found with id " + studentDTO.getId()));

        mapper.map(studentDTO, existingStudent);
        System.out.println(existingStudent);

        List<Student> studentList = studentRepository.findAll();
        TypeToken<List<StudentDTO>> typeToken = new TypeToken<>() {
        };
        List<StudentDTO> studentDTOList = mapper.map(studentList, typeToken.getType());
        List<Student> students = calculateRank(studentDTOList);

        studentRepository.save(existingStudent);
        TypeToken<StudentDTO> token = new TypeToken<>() {
        };
        // Casting student class to StudentDTO
        return mapper.map(existingStudent, token.getType());

    }

}
