package com.example.LearningCenter.service;


import com.example.LearningCenter.dto.StudentCourseMarkDTO;
import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.CourseEntity;
import com.example.LearningCenter.entitty.StudentCourseMarkEntity;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.exception.AppBadRequestException;
import com.example.LearningCenter.repositroy.CourseRepository;
import com.example.LearningCenter.repositroy.StudentCourseMarkRepository;
import com.example.LearningCenter.repositroy.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto) {

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        CourseEntity courseEntity = courseRepository.findById(dto.getCourse_id()).get();
        StudentEntity studentEntity = studentRepository.findById(dto.getStudent_id()).get();

        if (courseEntity == null) {
            throw new AppBadRequestException("not this course ? ? ? ");
        }
        if (studentEntity == null) {
            throw new AppBadRequestException("not this student ? ? ?");
        }
        if (dto.getMark() == null) {
            throw new AppBadRequestException("not this MARK  ? ? ?");
        }
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        entity.setMark(dto.getMark());

        studentCourseMarkRepository.save(entity);
        return dto;
    }

    public StudentCourseMarkDTO get(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("StudentCourseMark not found: " + id);
        }
        return getStudentCourseMarkDTObyEntity(optional.get());
    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {

            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourse_id(entity.getCourse().getId());
            dto.setStudent_id(entity.getStudent().getId());
            dto.setMark(entity.getMark());
            dto.setCreated_date(entity.getCreated_date());

            dtoList.add(dto);
        });

        return dtoList;
    }

    public boolean update(Integer id, StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findById(id).get();
        CourseEntity courseEntity = courseRepository.findById(dto.getCourse_id()).get();
        StudentEntity studentEntity = studentRepository.findById(dto.getStudent_id()).get();

        entity.setMark(dto.getMark());
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        studentCourseMarkRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findById(id).get();
        studentCourseMarkRepository.delete(entity);
        return true;
    }

    public StudentCourseMarkDTO getStudentCourseMarkDTObyEntity(StudentCourseMarkEntity entity) {
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourse_id(entity.getCourse().getId());
        dto.setStudent_id(entity.getStudent().getId());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

//    public List<StudentCourseMarkEntity> getByDay(String day) {
//        return new LinkedList<>(studentCourseMarkRepository.findByCreated_date(LocalDateTime.parse(day)));
//    }
}
