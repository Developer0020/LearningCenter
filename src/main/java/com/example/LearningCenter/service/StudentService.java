package com.example.LearningCenter.service;

import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.exception.AppBadRequestException;
import com.example.LearningCenter.repositroy.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());

        if (dto.getName().isBlank() || dto.getName() == null) {
            throw new AppBadRequestException("name is null");
        }
        if (dto.getSurname().isBlank() || dto.getSurname() == null) {
            throw new AppBadRequestException("surname is null ???");
        }
        if (dto.getAge() == null) {
            throw new AppBadRequestException("Age is null ???");
        }
        if (dto.getLevel() == null) {
            throw new AppBadRequestException("level is null ???");
        }
        if (dto.getGender().isBlank() || dto.getGender() == null) {
            throw new AppBadRequestException("Gender is null ???");
        }

        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public StudentEntity getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }
}
