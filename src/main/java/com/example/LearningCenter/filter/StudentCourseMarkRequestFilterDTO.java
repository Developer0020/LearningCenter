package com.example.LearningCenter.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class StudentCourseMarkRequestFilterDTO {
    private Integer id;
    private Integer student_id;
    private Integer course_id;
    private Integer mark;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
