package com.example.LearningCenter.repositroy;


import com.example.LearningCenter.entitty.StudentCourseMarkEntity;
import com.example.LearningCenter.mapper.CourseInfoMapper;
import com.example.LearningCenter.mapper.StudentCourseMarkMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer>,
        PagingAndSortingRepository<StudentCourseMarkEntity, Integer> {
    List<StudentCourseMarkEntity> findAllByCreatedDate(LocalDateTime date);


    @Query("from StudentCourseMarkEntity where student.id= :student_id and createdDate>= :fromDate and createdDate<= :toDate")
    List<StudentCourseMarkEntity> findAllByStudentIdAndCreatedDateBetween(Integer student_id, LocalDateTime fromDate, LocalDateTime toDate);

    @Query("from StudentCourseMarkEntity where createdDate>= :day")
    List<StudentCourseMarkEntity> getByDate(LocalDateTime day);

    @Query("from StudentCourseMarkEntity where student.id= :student_id order by createdDate DESC ")
    List<StudentCourseMarkEntity> getByMark(Integer student_id);
    //Studentni olgan barcha baxolari vaqt boyicha kamayish tartibida sord qiling.

    @Query("from StudentCourseMarkEntity where student.id= :student_id and course.id= :course_id order by createdDate DESC")
    List<StudentCourseMarkEntity> getByCourseMark(Integer student_id, Integer course_id);

    @Query(value = "SELECT course_id from  student_course_mark where student_id = :studentId order by created_date desc limit 1 ", nativeQuery = true)
    Integer findLastCourseMarker(@Param("studentId") Integer studentId);

    @Query(value = "select mark from StudentCourseMarkEntity where student.id = :studentId order by createdDate asc limit 1")
    Integer getFirstMark(Integer studentId);

    @Query(value = "select mark from StudentCourseMarkEntity where student.id= :studentId and course.id= :courseId order by createdDate asc limit 1")
    Integer getFirstMarkByCourseId(Integer studentId, Integer courseId);

    @Query(value = "select mark from StudentCourseMarkEntity where student.id= :studentId and course.id= :courseId order by mark desc limit 1")
    Integer getStudentMaxMarkByCourse(Integer studentId, Integer courseId);

    @Query(value = "select avg (mark) from StudentCourseMarkEntity where student.id= :studentId  order by course.id ")
    List<Integer> getAVGMarkList(Integer studentId);

    @Query(value = "SELECT  c.name, scm.mark " +
            " from  student_course_mark as scm " +
            " inner join course as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    CourseInfoMapper findLastCourseMarkerAsNative(@Param("studentId") Integer studentId);

    @Query(value = "SELECT  c.name, scm.mark " +
            " from  student_course_mark as scm " +
            " inner join course as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.mark desc limit 3 ", nativeQuery = true)
    List<CourseInfoMapper> findLastMaxMarkerAsNative(@Param("studentId") Integer studentId);

    @Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
            "  c.id as cId, c.name as cName " +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    CourseInfoMapper findLastCourseMarkerAsNativeMapping(@Param("studentId") Integer studentId);

    @Query("select avg (mark)from StudentCourseMarkEntity where student.id= :studentId and course.id= :courseId")
    List<Integer> getAVGByCourseMark(Integer studentId, Integer courseId);

    @Query("select count (*)from StudentCourseMarkEntity where student.id= :studentId and mark> :mark")
    Integer getCountByMark(Integer studentId, Integer mark);

    @Query("select max (mark)from StudentCourseMarkEntity where course.id = :course_id")
    Integer getMaxMarkByCourse(Integer course_id);

    @Query("select avg (mark)from StudentCourseMarkEntity where course.id= :courseId")
    Integer getAVGMarkByCourse(Integer courseId);

    @Query("select count (mark)from StudentCourseMarkEntity where course.id= :courseId")
    Integer getCountByCourse(Integer courseId);

    Page<StudentCourseMarkEntity> findAllByStudentId(Integer studentId, Pageable paging);

    Page<StudentCourseMarkEntity> findAllByCourseId(Integer courseId, Pageable paging);
}