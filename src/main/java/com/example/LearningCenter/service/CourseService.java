package com.example.LearningCenter.service;

import com.example.LearningCenter.dto.CourseDTO;
import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.CourseEntity;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.exception.AppBadRequestException;
import com.example.LearningCenter.repositroy.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO create(CourseDTO dto) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setPrice(dto.getPrice());
        courseEntity.setDuration(dto.getDuration());
        courseEntity.setName(dto.getName());

        if (dto.getDuration() == null) {
            throw new AppBadRequestException("duration is null ???");
        }
        if (dto.getPrice() == null) {
            throw new AppBadRequestException("price is null ???");
        }
        if (dto.getName().isBlank() || dto.getName() == null) {
            throw new AppBadRequestException("name is null ???");
        }
        courseRepository.save(courseEntity);
        return dto;
    }

    public CourseEntity get(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + id);
        }
        return optional.get();
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreated_date());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setName(entity.getName());
            dtoList.add(dto);
        });

        return dtoList;
    }

    public boolean update(Integer id, CourseDTO dto) {
        CourseEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        courseRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        CourseEntity entity = get(id);
        courseRepository.delete(entity);
        return true;
    }
}
