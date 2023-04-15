package com.example.LearningCenter.controller;

import com.example.LearningCenter.dto.CourseDTO;
import com.example.LearningCenter.filter.CourseFilterRequestDTO;
import com.example.LearningCenter.dto.CourseFilterRequestPriceBetweenDTO;
import com.example.LearningCenter.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.create(dto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> getAll() {
        return ResponseEntity.ok(new LinkedList<>(courseService.getAll()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping("/paging")
    public ResponseEntity<?>paging(
            @RequestParam (value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "30") int size){
    return ResponseEntity.ok(courseService.paging(page,size));
    }

    //  11. Course Pagination by price. List should be sorted by createdDate.
    @PostMapping("/paging-price")
    public ResponseEntity<?>pagingWithPrice(
            @RequestParam (value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "30") int size,
            @RequestBody CourseFilterRequestDTO dto){
        return ResponseEntity.ok(courseService.pagingWithPrice(dto.getPrice(),page,size));
    }

    //  12.  Course Pagination by price between. List should be sorted by createdDate.
    @PostMapping("/paging-price-between")
    public ResponseEntity<?>pagingWithBetweenPrice(
            @RequestParam (value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "30") int size,
            @RequestBody CourseFilterRequestPriceBetweenDTO dto){
        return ResponseEntity.ok(courseService.pagingWithBetweenPrice(dto.getFromPrice(),dto.getToPrice(),page,size));
    }
}
