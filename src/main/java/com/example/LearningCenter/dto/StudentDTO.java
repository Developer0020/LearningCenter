package com.example.LearningCenter.dto;

import com.example.LearningCenter.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private String gender;
    private LocalDate createdDate;
}
