package com.example.sb_assign_16_05_23.dto;
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
public class Pair<T> {
    private T firstStudentName;
    private T secondStudentName;
}
