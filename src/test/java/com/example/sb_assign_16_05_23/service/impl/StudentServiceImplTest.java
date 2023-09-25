package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.Pair;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.entity.Student;
import com.example.sb_assign_16_05_23.errors.BadRequestException;
import com.example.sb_assign_16_05_23.errors.NotFoundException;
import com.example.sb_assign_16_05_23.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Pair found")
    public void testGetStudentPairEqualsToSum_Success() {
        // Arrange
        Double target = 100.0;

        List<Student> studentList = Arrays.asList(
                new Student(1L, "John", 40.0, 3),
                new Student(2L, "Jane", 60.0, 2),
                new Student(3L, "David", 30.0, 4),
                new Student(4L, "Emma", 70.0, 1)
        );

        List<StudentDTO> studentDtoList = Arrays.asList(
                new StudentDTO(1L, "John", 40.0, 3),
                new StudentDTO(2L, "Jane", 60.0, 2),
                new StudentDTO(3L, "David", 30.0, 4),
                new StudentDTO(4L, "Emma", 70.0, 1)
        );

        when(studentRepository.findAll()).thenReturn(studentList);
        when(mapper.map(any(Student.class), eq(StudentDTO.class)))
                .thenReturn(studentDtoList.get(0))
                .thenReturn(studentDtoList.get(1))
                .thenReturn(studentDtoList.get(2))
                .thenReturn(studentDtoList.get(3));

        // Act
        List<Pair<String>> result = studentService.getStudentPairEqualsToSum(target);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(new Pair<>("John", "Jane"), result.get(0));
        assertEquals(new Pair<>("David", "Emma"), result.get(1));

        verify(studentRepository, times(1)).findAll();
        verify(mapper, times(4)).map(any(Student.class), eq(StudentDTO.class));
    }

    @Test
    @DisplayName("No Pair found")
    public void testGetStudentPairEqualsToSum_NoPairFound() {
        // Arrange
        Double target = 150.0;

        List<Student> studentList = Arrays.asList(
                new Student(1L, "John", 40.0, 3),
                new Student(2L, "Jane", 60.0, 2),
                new Student(3L, "David", 30.0, 4),
                new Student(4L, "Emma", 70.0, 1)
        );

        List<StudentDTO> studentDtoList = Arrays.asList(
                new StudentDTO(1L, "John", 40.0, 3),
                new StudentDTO(2L, "Jane", 60.0, 2),
                new StudentDTO(3L, "David", 30.0, 4),
                new StudentDTO(4L, "Emma", 70.0, 1)
        );

        when(studentRepository.findAll()).thenReturn(studentList);
        when(mapper.map(any(Student.class), eq(StudentDTO.class)))
                .thenReturn(studentDtoList.get(0))
                .thenReturn(studentDtoList.get(1))
                .thenReturn(studentDtoList.get(2))
                .thenReturn(studentDtoList.get(3));

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> studentService.getStudentPairEqualsToSum(target));
        assertEquals("There is no pair of Students whose sum is equal to " + target, exception.getMessage());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Bad Request ")
    public void testGetStudentPairEqualsToSum_TargetGreaterThanSum() {
        // Arrange
        Double target = 3000.0;

        List<Student> studentList = Arrays.asList(
                new Student(1L, "John", 40.0, 3),
                new Student(2L, "Jane", 60.0, 2),
                new Student(3L, "David", 30.0, 4),
                new Student(4L, "Emma", 70.0, 1)
        );

        List<StudentDTO> studentDtoList = Arrays.asList(
                new StudentDTO(1L, "John", 40.0, 3),
                new StudentDTO(2L, "Jane", 60.0, 2),
                new StudentDTO(3L, "David", 30.0, 4),
                new StudentDTO(4L, "Emma", 70.0, 1)
        );

        when(studentRepository.findAll()).thenReturn(studentList);
        when(mapper.map(any(Student.class), eq(StudentDTO.class)))
                .thenReturn(studentDtoList.get(0))
                .thenReturn(studentDtoList.get(1))
                .thenReturn(studentDtoList.get(2))
                .thenReturn(studentDtoList.get(3));


        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> studentService.getStudentPairEqualsToSum(target));
        assertEquals("Target Should be less then 1200", exception.getMessage());

        verify(studentRepository, times(1)).findAll();
    }
}
