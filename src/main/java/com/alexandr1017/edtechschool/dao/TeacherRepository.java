package com.alexandr1017.edtechschool.dao;

import com.alexandr1017.edtechschool.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(value = "SELECT c.* FROM course c WHERE c.teacher_id = :teacherId",nativeQuery = true)
    List<Object[]> findCoursesForTeacher(@Param("teacherId")Integer teacherId);
}
