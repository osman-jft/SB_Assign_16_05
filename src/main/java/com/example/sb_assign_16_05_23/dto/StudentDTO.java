package com.example.sb_assign_16_05_23.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    //student DTO (Data Transfer Object) to transfer data from DB between different layers of the application
    //namely controller, service, entity, etc.

    //the fields of DTO match the Entity; "Student" in this case
    Long id;

    String name;

    Double marks;

    Integer rank;

}
