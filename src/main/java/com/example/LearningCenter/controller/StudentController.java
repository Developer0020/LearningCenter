package com.example.LearningCenter.controller;

import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> list = studentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentEntity entity = studentService.getById(id);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<StudentEntity>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping("/getBySurname/{surname}")
    public ResponseEntity<List<StudentEntity>> getBySurname(@PathVariable("surname") String surname) {
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }

    @GetMapping("/getByLevel/{level}")
    public ResponseEntity<List<StudentEntity>> getByLevel(@PathVariable("level") Integer level) {
        return ResponseEntity.ok(studentService.getByLevel(level));
    }

    @GetMapping("/getByAge/{age}")
    public ResponseEntity<List<StudentEntity>> getByAge(@PathVariable("age") Integer age) {
        return ResponseEntity.ok(studentService.getByAge(age));
    }

    @GetMapping("/getByGender/{gender}")
    public ResponseEntity<List<StudentEntity>> getByGender(@PathVariable("gender") String gender) {
        return ResponseEntity.ok(studentService.getByGender(gender));
    }

    @GetMapping("/getByGivenDate/{date}")
    public ResponseEntity<List<StudentEntity>> getByGivenDate(@PathVariable("date") String date) {
        return ResponseEntity.ok(studentService.getByGivenDate(date));
    }

    @GetMapping("/getByBetweenGivenDates")
    public ResponseEntity<List<StudentEntity>> getByBetweenGivenDates(@RequestParam("dateFrom") String dateFrom,
                                                                      @RequestParam("dateTo") String dateTo) {
        return ResponseEntity.ok(studentService.getByBetweenGivenDates(dateFrom, dateTo));
    }

}
