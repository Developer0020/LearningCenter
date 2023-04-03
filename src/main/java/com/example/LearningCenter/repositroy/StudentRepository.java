package com.example.LearningCenter.repositroy;

import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity,Integer> {
}
