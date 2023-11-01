package com.alexandr1017.edtechschool.dao;

import com.alexandr1017.edtechschool.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementTeacherRepository extends CrudRepository<Course,Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE course c SET c.teacher_id = :teacherId WHERE c.id = :courseId",nativeQuery = true)
    void addTeacherToCourse(@Param("teacherId") int teacherId, @Param("courseId") int courseId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE course c SET c.teacher_id = :teacherId WHERE c.id = :courseId",nativeQuery = true)
    void replaceTeacherOnCourse(@Param("teacherId") int teacherId, @Param("courseId") int courseId);
}
