package com.example.LearningCenter.entitty;

import com.example.LearningCenter.dto.CourseDTO;
import com.example.LearningCenter.dto.StudentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "student_course_mark")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @Column(name = "mark")
    private Integer mark;
    @Column(name = "created_date")
    private LocalDateTime created_date=LocalDateTime.now();
}
