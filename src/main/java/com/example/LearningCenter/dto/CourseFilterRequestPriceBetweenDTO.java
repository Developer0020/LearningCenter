package com.example.LearningCenter.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseFilterRequestPriceBetweenDTO {
    private Double fromPrice;
    private Double toPrice;
    private Integer id;
    private Integer student_id;
    private Integer course_id;
    private Integer mark;
    private LocalDateTime createdDate;
}
