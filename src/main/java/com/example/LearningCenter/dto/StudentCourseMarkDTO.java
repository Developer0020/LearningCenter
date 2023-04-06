package com.example.LearningCenter.dto;

import com.example.LearningCenter.entitty.CourseEntity;
import com.example.LearningCenter.entitty.StudentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentCourseMarkDTO {
    private Integer id;
    private Integer student_id;
    private Integer course_id;
    private Integer mark;
    private LocalDateTime created_date;
}
//id,studentId,courseId,mark,createdDate