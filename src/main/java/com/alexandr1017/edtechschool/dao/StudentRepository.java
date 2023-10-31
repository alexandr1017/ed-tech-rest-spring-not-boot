package com.alexandr1017.edtechschool.dao;

import com.alexandr1017.edtechschool.model.Course;
import com.alexandr1017.edtechschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT c.* FROM course c JOIN course_student cs ON c.id = cs.course_id WHERE cs.student_id = :studentId",nativeQuery = true)
    List<Object[]> findCoursesForStudent(@Param("studentId")Integer studentId);
}
