package com.example.LearningCenter.service;

import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.exception.AppBadRequestException;
import com.example.LearningCenter.repositroy.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public boolean update(Integer id, StudentDTO dto) {
        StudentEntity entity = getById(id);
        if (dto ==null){
            throw new AppBadRequestException("dto is null ? ? ?");
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        studentRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        StudentEntity entity = getById(id);
        studentRepository.delete(entity);
        return true;
    }

    public List<StudentEntity> getByName(String name) {
        return new LinkedList<>(studentRepository.findByName(name));
    }

    public List<StudentEntity> getBySurname(String surname) {
        return new LinkedList<>(studentRepository.findBySurname(surname));
    }

    public List<StudentEntity> getByLevel(Integer level) {
        return new LinkedList<>(studentRepository.findByLevel(level));
    }

    public List<StudentEntity> getByAge(Integer age) {
        return new LinkedList<>(studentRepository.findByAge(age));
    }

    public List<StudentEntity> getByGender(String gender) {
        return new LinkedList<>(studentRepository.findByGender(gender));
    }

    public List<StudentEntity> getByGivenDate(String date) {
        return new LinkedList<>(studentRepository.findByCreatedDate(LocalDate.parse(date)));
    }

    public List<StudentEntity> getByBetweenGivenDates(String dateFrom, String dateTo) {
        return new LinkedList<>(studentRepository.findByCreatedDateBetween(LocalDate.parse(dateFrom), LocalDate.parse(dateTo)));
    }
}
