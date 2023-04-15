package com.example.LearningCenter.repositroy;

import com.example.LearningCenter.entitty.StudentEntity;
import com.example.LearningCenter.filter.CourseFilterRequestDTO;
import com.example.LearningCenter.filter.StudentFilterRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseCustomRepository {
    @Autowired
    private EntityManager entityManager;
    public List<StudentEntity> paging(CourseFilterRequestDTO filterDTO) {

        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select c From CourseEntity as c where visible =true");

        if (filterDTO.getName() != null) {
            builder.append(" and c.name = :name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getId() != null) {
            builder.append(" and c.id =: id");
            params.put("id", filterDTO.getId());
        }
        if (filterDTO.getPrice()!=null){
            builder.append(" and c.price = :price");
            params.put("price",filterDTO.getPrice());
        }
        if (filterDTO.getDuration()!=null){
            builder.append(" and c.duration = :duration");
            params.put("duration",filterDTO.getDuration());
        }
        if (filterDTO.getDateFrom() != null && filterDTO.getDateTo() != null) {
            builder.append(" and s.createdDate between :dateFrom and :dateTo ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));
            params.put("dateTo", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MAX));
        }
        else if (filterDTO.getDateFrom() != null) {
            builder.append(" and s.createdDate >= :dateFrom ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));
        }
        else if (filterDTO.getDateTo() != null) {
            builder.append(" and s.createdDate <= :dateTo ");
            params.put("dateTo", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MIN));
        }
        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        List studentList = query.getResultList();
        return studentList;
    }
//id,name,price,duration,date
}
