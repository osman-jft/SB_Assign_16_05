package com.example.sb_assign_16_05_23.controller;
import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.dto.StudentDTO;
import com.example.sb_assign_16_05_23.service.StudentService;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    //student controller to create endpoint /api/students

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    private ResponseDTO<List<StudentDTO>> getStudents() {
        //returns list of students from StudentService
        return ResponseDTO.<List<StudentDTO>>builder().data(studentService.getAllStudents())
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }

    @PutMapping("/{sid}")
    private ResponseDTO<StudentDTO> updateStudent(@PathVariable Long sid, @RequestBody StudentDTO student) {

        if (!sid.equals(student.getId())) {
            return  ResponseDTO.<StudentDTO>builder().data(student).message(Constants.BAD_REQUEST_MSG).status(HttpStatus.BAD_REQUEST.value()).build();
        }
        return ResponseDTO.<StudentDTO>builder().data(studentService.updateStudent(student))
                        .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
}
