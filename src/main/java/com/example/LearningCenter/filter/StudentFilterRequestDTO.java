package com.example.LearningCenter.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentFilterRequestDTO {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String gender;
}

