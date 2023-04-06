package com.example.LearningCenter.controller;

import com.example.LearningCenter.dto.CourseDTO;
import com.example.LearningCenter.dto.StudentCourseMarkDTO;
import com.example.LearningCenter.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/studentCourseMark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(studentCourseMarkService.create(dto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentCourseMarkDTO>> getAll() {
        return ResponseEntity.ok(new LinkedList<>(studentCourseMarkService.getAll()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(studentCourseMarkService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }

//    @GetMapping("/getByDay/{day}")
//    public ResponseEntity<?> getByDay(@PathVariable("day") String day) {
//        return ResponseEntity.ok(studentCourseMarkService.getByDay(day));
//    }

}
