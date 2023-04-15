package com.example.LearningCenter.entitty;

import com.example.LearningCenter.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "level")
    private Integer level;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();
    @Column(name = "visible")
    private Boolean visible = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private Set<StudentCourseMarkEntity> studentSet;

    public StudentEntity() {
    }

    public StudentEntity(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
