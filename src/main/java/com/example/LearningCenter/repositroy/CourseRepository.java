package com.example.LearningCenter.repositroy;

import com.example.LearningCenter.entitty.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>,
        PagingAndSortingRepository<CourseEntity, Integer> {
    Page<CourseEntity> findAll(Pageable pageable);

    Page<CourseEntity> findAllByPrice(Double price, Pageable pageable);

    Page<CourseEntity> findAllByPriceBetween(Double fromPrice, Double toPrice, Pageable pageable);
}
