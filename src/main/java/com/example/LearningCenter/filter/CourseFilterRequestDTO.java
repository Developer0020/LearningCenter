package com.example.LearningCenter.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseFilterRequestDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer duration;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
