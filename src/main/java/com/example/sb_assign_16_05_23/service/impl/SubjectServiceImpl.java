package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.dto.SubjectListDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.entity.Teacher;
import com.example.sb_assign_16_05_23.errors.NotFoundException;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    private final EntityManager em;
   


        /**
         *How to create a dynamic query in pring for paginated and filter API
         * Hibernate Criteria is one of the ways through whih it is done.---rnD
         * NAtive Query - M2.
         * Calendar API
         * LocalDate API -java8 feature.
         */



    private List<SubjectDTO> getSubjectDTOS(Page<Subject> subjectPage) {
        if(subjectPage.hasContent()) {
            List<Subject> subjectList = subjectPage.getContent();
            System.out.println("hh");
            List<SubjectDTO> subjectDTOList = subjectList.stream()
                    .map(subject -> this.modelMapper.map(subject, SubjectDTO.class)).toList();
            subjectDTOList.forEach(subject -> System.out.println(subject));
            return subjectDTOList;
        }
        throw new NotFoundException("Record not found");
    }
    public Page<Subject> findByDetails(String name,Timestamp fromDate,Timestamp toDate,PageRequest p ){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Subject> cq=cb.createQuery(Subject.class);
        Root<Subject> subject=cq.from(Subject.class);
        Join<Subject, Teacher> subjectTeacherJoin=subject.join("teacher");
        List<Predicate> predicateList=new ArrayList<>();
        if(name!=null&&name!="")
        predicateList.add(cb.equal(subjectTeacherJoin.get("name"),name));
        if(fromDate!=null)
        predicateList.add(cb.greaterThanOrEqualTo(subject.get("fromDate"),fromDate));
        if(toDate!=null)
        predicateList.add(cb.lessThanOrEqualTo(subject.get("toDate"),toDate));
        cq.where(cb.and(predicateList.toArray(predicateList.toArray(new Predicate[predicateList.size()]))));
        cq.orderBy(cb.asc(subject.get("name")));
               List<Subject> resultList = em.createQuery(cq).setFirstResult((int) p.getOffset())
                .setMaxResults(p.getPageSize()).getResultList();

        return new PageImpl<>(resultList,p,resultList.size());
    }


    @Override
    public List<SubjectDTO> getAllSubjectsByTeacherAndFromDateAndToDate(SubjectListDTO subjectListDTO) {

        Timestamp from = null, to = null;
        Instant fromDate=null,toDate=null;
        if (subjectListDTO.getFromDate() != null){
             fromDate = Instant.ofEpochSecond(subjectListDTO.getFromDate());
             from=Timestamp.from(fromDate);
        }
        if(subjectListDTO.getToDate()!=null) {
            toDate = Instant.ofEpochSecond(subjectListDTO.getToDate());
            to=Timestamp.from(toDate);
        }
        PageRequest p = PageRequest.of(subjectListDTO.getPageNo(), subjectListDTO.getNoOfRecords());

        Page<Subject> subjectPage= this.
                findByDetails(subjectListDTO.getTeacherName(),from,to,p);
        return getSubjectDTOS(subjectPage);
    }
}
