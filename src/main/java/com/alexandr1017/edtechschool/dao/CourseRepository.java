package com.alexandr1017.edtechschool.dao;

import com.alexandr1017.edtechschool.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query(value = "SELECT s.* FROM student s JOIN course_student cs ON s.id = cs.student_id WHERE cs.course_id = :courseId", nativeQuery = true)
    List<Object[]> findStudentsByCourse(@Param("courseId") Integer courseId);

}