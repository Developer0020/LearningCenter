package com.example.LearningCenter.repositroy;

import com.example.LearningCenter.entitty.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {
    List<StudentEntity> findByName(String name);

    List<StudentEntity> findBySurname(String surname);

    List<StudentEntity> findByLevel(Integer level);

    List<StudentEntity> findByAge(Integer age);

    List<StudentEntity> findByGender(String gender);

    List<StudentEntity> findByCreatedDate(LocalDate date);

    List<StudentEntity> findByCreatedDateBetween(LocalDate dateFrom, LocalDate dateTo);

    @Transactional
    @Modifying
    @Query("update StudentEntity set surname =:surname where id= :sId")
    Integer changeVisibility(@Param("sId") Integer sId, @Param("surname") String surname);

    @Query("from StudentEntity where name like ?1")
    List<StudentEntity> findByName2(String name);

    @Query("select new StudentEntity (id,name,surname)from StudentEntity ")
    List<StudentEntity> findByName4();

    Page<StudentEntity> findAllByName(String name, Pageable pageable);

    Page<StudentEntity> findAllByLevel(Integer level, Pageable pageable);

    Page<StudentEntity> findAllByGender(String gender, Pageable pageable);

//    @Query("SELECT new com.example.LearningCenter.mapper.StudentMapper(id,name,phone) FROM StudentEntity ")
//    List<StudentMapper> findByName5();
//    List<StudentEntity>findAllByMarkIn(List<String> phoneList);
}
