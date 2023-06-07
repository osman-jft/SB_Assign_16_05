package com.example.sb_assign_16_05_23.repository;
;
import com.example.sb_assign_16_05_23.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Page<Subject> findByTeacherName(String name, Pageable p);
}
