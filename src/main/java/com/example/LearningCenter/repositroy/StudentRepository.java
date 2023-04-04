package com.example.LearningCenter.repositroy;

import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    List<StudentEntity> findByName(String name);

    List<StudentEntity> findBySurname(String surname);

    List<StudentEntity> findByLevel(Integer level);

    List<StudentEntity> findByAge(Integer age);

    List<StudentEntity> findByGender(String gender);

    List<StudentEntity> findByCreatedDate(LocalDate date);

    List<StudentEntity>findByCreatedDateBetween(LocalDate dateFrom,LocalDate dateTo);
}
