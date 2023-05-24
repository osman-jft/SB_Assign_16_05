package com.example.sb_assign_16_05_23.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidList<T> implements List<T> {
    @Valid
    @Delegate
    private List<T> list ;

    public ValidList() {
        this.list = new ArrayList<T>();
    }

    public ValidList(List<T> list) {
        this.list = list;
    }

}
