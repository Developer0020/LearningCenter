package com.example.LearningCenter.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourseMarkMapper {
    private String courseName;
    private Integer mark;

    public StudentCourseMarkMapper(String courseName, Integer mark) {
        this.courseName = courseName;
        this.mark = mark;
    }

    public StudentCourseMarkMapper() {
    }
}
