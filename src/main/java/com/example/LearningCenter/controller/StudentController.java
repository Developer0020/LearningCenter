package com.example.LearningCenter.controller;

import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO) {
        StudentDTO response = studentService.create(studentDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAll(){
        List<StudentDTO>list=studentService.getAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/get{id}")
    public ResponseEntity<?>getById(@PathVariable("id") Integer id){
        StudentEntity entity = studentService.getById(id);
        return ResponseEntity.ok(entity);
    }
}
