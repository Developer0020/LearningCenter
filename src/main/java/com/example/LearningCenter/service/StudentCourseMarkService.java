package com.example.LearningCenter.service;


import com.example.LearningCenter.dto.CourseDTO;
import com.example.LearningCenter.dto.StudentCourseMarkDTO;
import com.example.LearningCenter.dto.StudentDTO;
import com.example.LearningCenter.entitty.CourseEntity;
import com.example.LearningCenter.entitty.StudentCourseMarkEntity;
import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.exception.AppBadRequestException;
import com.example.LearningCenter.mapper.CourseInfoMapper;
import com.example.LearningCenter.mapper.StudentCourseMarkMapper;
import com.example.LearningCenter.repositroy.CourseRepository;
import com.example.LearningCenter.repositroy.StudentCourseMarkRepository;
import com.example.LearningCenter.repositroy.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
            dtoList.add(getStudentCourseMarkDTObyEntity(entity));
        });

        return dtoList;
    }

    public StudentCourseMarkDTO update(Integer id, StudentCourseMarkDTO dto) {

        StudentCourseMarkEntity entity = studentCourseMarkRepository.findById(id).get();

        CourseEntity courseEntity = courseRepository.findById(dto.getCourse_id()).get();
        StudentEntity studentEntity = studentRepository.findById(dto.getStudent_id()).get();

        entity.setMark(dto.getMark());
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        studentCourseMarkRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
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
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setMark(entity.getMark());
        return dto;
    }

    public List<StudentCourseMarkDTO> getByDay(String day) {
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        List<StudentCourseMarkEntity> resultList = (studentCourseMarkRepository.getByDate(LocalDateTime.of(LocalDate.parse(day), LocalTime.now())));
        resultList.forEach(entity -> dtoList.add(getStudentCourseMarkDTObyEntity(entity)));
        return dtoList;
    }

    public List<StudentCourseMarkDTO> getByBetweenDay(Integer studentId, LocalDate fromDate, LocalDate toDate) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findAllByStudentIdAndCreatedDateBetween(studentId,
                LocalDateTime.of(fromDate, LocalTime.MIN),
                LocalDateTime.of(toDate, LocalTime.MAX));
        List<StudentCourseMarkDTO> resultList = new LinkedList<>();
        entityList.forEach(entity -> {
            resultList.add(getStudentCourseMarkDTObyEntity(entity));
        });
        return resultList;
    }

    public List<StudentCourseMarkDTO> getByMark(Integer id) {
        List<StudentCourseMarkDTO> resultList = new LinkedList<>();
        studentCourseMarkRepository.getByMark(id).forEach(entity -> {
            resultList.add(getStudentCourseMarkDTObyEntity(entity));
        });
        return resultList;
    }

    public List<StudentCourseMarkDTO> getByCourseMark(Integer studentId, Integer courseId) {
        List<StudentCourseMarkDTO> resultList = new LinkedList<>();
        studentCourseMarkRepository.getByCourseMark(studentId, courseId).forEach(entity -> {
            resultList.add(getStudentCourseMarkDTObyEntity(entity));
        });
        return resultList;
    }

//    public void test() {
//        List<Object[]> courseObjList = studentCourseMarkRepository.findLastCourseMarkerAsNative(1);
//        if (courseObjList.size() > 0) {
//            Object[] courseObj = courseObjList.get(0);
//
//            CourseDTO courseDTO = new CourseDTO();
//            courseDTO.setId((Integer) courseObj[0]);
//            courseDTO.setName((String) courseObj[1]);
//            System.out.println(courseDTO);
//        }
//
//        System.out.println("dasda");
//    }

    public void test2() {
        CourseInfoMapper courseInfoMapper = studentCourseMarkRepository.findLastCourseMarkerAsNativeMapping(1);
        if (courseInfoMapper != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseInfoMapper.getCid());
            courseDTO.setName(courseInfoMapper.getName());
            System.out.println(courseDTO + " " + courseInfoMapper.getMark());
        }

        System.out.println("dasda");
    }

    public StudentCourseMarkMapper lastCourseMarker(Integer id) {

        CourseInfoMapper mapper = studentCourseMarkRepository.findLastCourseMarkerAsNative(id);
        StudentCourseMarkMapper studentCourseMarkMapper = new StudentCourseMarkMapper();

        if (mapper != null) {
            studentCourseMarkMapper.setCourseName(mapper.getName());
            studentCourseMarkMapper.setMark(mapper.getMark());
        }

        return studentCourseMarkMapper;
    }

    public List<StudentCourseMarkMapper> lastMaxMarkList(Integer id) {
        List<CourseInfoMapper> mapperList = studentCourseMarkRepository.findLastMaxMarkerAsNative(id);
        List<StudentCourseMarkMapper> resultList = new LinkedList<>();
        for (CourseInfoMapper result : mapperList) {
            if (result != null) {
                StudentCourseMarkMapper mapper = new StudentCourseMarkMapper();
                mapper.setMark(result.getMark());
                mapper.setCourseName(result.getName());
                resultList.add(mapper);
            }
        }
        return resultList;
    }

    public Integer getFirstMark(Integer id) {
        return studentCourseMarkRepository.getFirstMark(id);
    }

    public Integer getFirstMarkByCourseId(Integer studentId, Integer courseId) {
        return studentCourseMarkRepository.getFirstMarkByCourseId(studentId, courseId);
    }

    public Integer getStudentMaxMarkByCourse(Integer studentId, Integer courseId) {
        return studentCourseMarkRepository.getStudentMaxMarkByCourse(studentId, courseId);
    }

    public List<Integer> getAVGMarkList(Integer id) {
        return studentCourseMarkRepository.getAVGMarkList(id);
    }

    public List<Integer> getAVGByCourseMark(Integer studentId, Integer courseId) {
        return studentCourseMarkRepository.getAVGByCourseMark(studentId, courseId);
    }

    public Integer getCountByMark(Integer studentId, Integer mark) {
        return studentCourseMarkRepository.getCountByMark(studentId, mark);
    }

    public Integer getMaxMarkByCourse(Integer courseId) {
        return studentCourseMarkRepository.getMaxMarkByCourse(courseId);
    }

    public Integer getAVGMarkByCourse(Integer courseId) {
        return studentCourseMarkRepository.getAVGMarkByCourse(courseId);
    }

    public Integer getCountByCourse(Integer courseId) {
        return studentCourseMarkRepository.getCountByCourse(courseId);
    }

    public Page<StudentCourseMarkDTO> paging(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAll(paging);

        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        Long totalCount = pageObj.getTotalElements();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setStudent_id(entity.getStudent().getId());
            dto.setCourse_id(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setId(entity.getId());
            dtoList.add(dto);
        }

        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentCourseMarkDTO> pagingWithStudentId(int page, int size, Integer studentId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByStudentId(studentId,paging);

        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        Long totalCount = pageObj.getTotalElements();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setStudent_id(entity.getStudent().getId());
            dto.setCourse_id(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setId(entity.getId());
            dtoList.add(dto);
        }

        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, paging, totalCount);
        return response;
    }
    public Page<StudentCourseMarkDTO> pagingWithCourseId(int page, int size, Integer courseId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByCourseId(courseId,paging);

        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        Long totalCount = pageObj.getTotalElements();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setStudent_id(entity.getStudent().getId());
            dto.setCourse_id(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setId(entity.getId());
            dtoList.add(dto);
        }

        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, paging, totalCount);
        return response;
    }

}
