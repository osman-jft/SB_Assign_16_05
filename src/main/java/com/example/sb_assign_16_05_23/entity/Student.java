package com.example.sb_assign_16_05_23.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    //here we are creating an entity "student" which will create a table in the DB by the same name

    //It will automatically create columns in the table - id, student_name, marks

    //lombok is taking care of the constructors/getters/setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "marks")
    private Double marks;

    @Column(name = "ranking")
    private Integer rank;

}
