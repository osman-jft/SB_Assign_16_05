package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.SubjectDTO;
import com.example.sb_assign_16_05_23.entity.Subject;
import com.example.sb_assign_16_05_23.repository.SubjectRepository;
import com.example.sb_assign_16_05_23.repository.TeacherRepository;
import com.example.sb_assign_16_05_23.service.SubjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SubjectDTO> getAllSubjects(String teacherName,Integer pageNo,Integer records){
        Pageable p= PageRequest.of(pageNo,records);
        Page<Subject> subjectPage= subjectRepository.findByTeacherName(teacherName,p);
        List<Subject> subjectList=subjectPage.getContent();
        List<SubjectDTO> subjectDTOList=subjectList.stream().map(subject -> this.modelMapper.map(subject,SubjectDTO.class)).toList();
        return subjectDTOList;
    }
}
