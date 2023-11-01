package com.alexandr1017.edtechschool.dao;

import com.alexandr1017.edtechschool.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementStudentRepository extends CrudRepository<Student, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO course_student (course_id, student_id) VALUES (:courseId, :studentId)", nativeQuery = true)
    void addStudentToCourse(@Param("studentId") int studentId, @Param("courseId") int courseId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM course_student WHERE course_id = :courseId AND student_id = :studentId", nativeQuery = true)
    void removeStudentFromCourse(@Param("studentId") int studentId, @Param("courseId") int courseId);

}
