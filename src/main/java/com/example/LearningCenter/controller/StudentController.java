package com.example.LearningCenter.controller;

import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.filter.StudentFilterRequestDTO;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/getByName")
    public ResponseEntity<List<StudentEntity>> getByName(@RequestParam("name") String name) {
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

    @PostMapping("/paging")
    public ResponseEntity<?> paging(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "30") int size,
            @RequestBody StudentFilterRequestDTO dto) {
        return ResponseEntity.ok(studentService.paging(dto, page, size));

    }

    @PostMapping(value = "/paging-name")
    public ResponseEntity<Page<StudentDTO>> pagingWithName(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "30") int size,
                                                           @RequestBody StudentFilterRequestDTO filter) {
        Page<StudentDTO> response = studentService.paginationWithName(filter.getName(), page, size);
        return ResponseEntity.ok(response);
    }

    // Student Pagination by given Level. List should be sorted by id.
    @GetMapping("/paging-Level")
    public ResponseEntity<Page<StudentDTO>> pagingWithLevel(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "30") int size,
                                                            @RequestParam(value = "level") Integer level) {
        Page<StudentDTO> response = studentService.pagingWithLevel(level, page, size);
        return ResponseEntity.ok(response);
    }

    // Pagination by given gender.  List should be sorted by createdDate.
    @PostMapping("/paging-gender")
    public ResponseEntity<Page<StudentDTO>> pagingWithGender(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "30") int size,
                                                             @RequestBody StudentFilterRequestDTO filter) {
        Page<StudentDTO> response = studentService.pagingWithGender(filter.getGender(), page, size);
        return ResponseEntity.ok(response);
    }

//    @PostMapping(value = "/filter")
//    public ResponseEntity<?> filter(@RequestBody StudentFilterRequestDTO filterDTO) {
//        studentService.filter(filterDTO);
//        return ResponseEntity.ok().build();
//    }
}
