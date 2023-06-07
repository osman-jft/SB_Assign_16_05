package com.example.sb_assign_16_05_23.repository;

import com.example.sb_assign_16_05_23.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//By extending JPARepo we reduce boilerplate code
//We no longer need to create boilerplate methods like findAll()
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByOrderByStudentRank();

    Student findFirstByOrderByMarks();

    List<Student> findAllByMarksLessThanEqualOrderByMarksDesc(Double marks);

}
