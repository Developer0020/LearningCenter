package com.example.LearningCenter.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer duration;
    private LocalDate createdDate;
}

//id,name,price,duration,date
