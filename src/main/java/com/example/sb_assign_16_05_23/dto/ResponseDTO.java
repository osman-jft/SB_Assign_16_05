package com.example.sb_assign_16_05_23.dto;
import com.example.sb_assign_16_05_23.entity.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonView(Views.TeacherView.class)
public class ResponseDTO<T> {

    private T data;
    private int status;
    private String message;

}

