package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidList<T> implements List<T> {
    // use to validate the list of DTO
    @Valid
    @Delegate
    private List<T> list = new ArrayList<>();

}
