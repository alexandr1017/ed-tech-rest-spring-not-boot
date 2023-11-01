package com.alexandr1017.edtechschool.service.impl;

import com.alexandr1017.edtechschool.dao.CourseRepository;
import com.alexandr1017.edtechschool.dao.ManagementStudentRepository;
import com.alexandr1017.edtechschool.dao.StudentRepository;
import com.alexandr1017.edtechschool.exception.ItemNotFoundException;
import com.alexandr1017.edtechschool.model.Course;
import com.alexandr1017.edtechschool.model.Student;
import com.alexandr1017.edtechschool.model.Teacher;
import com.alexandr1017.edtechschool.service.ManagementStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementStudentServiceImpl implements ManagementStudentService {


    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ManagementStudentRepository managementStudentRepository;

    @Autowired
    public ManagementStudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, ManagementStudentRepository managementStudentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.managementStudentRepository = managementStudentRepository;
    }


    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        searchedOrThrowExc(studentId, courseId);

        managementStudentRepository.addStudentToCourse(studentId, courseId);
    }

    @Override
    public void removeStudentFromCourse(int studentId, int courseId) {
        searchedOrThrowExc(studentId, courseId);

        managementStudentRepository.removeStudentFromCourse(studentId, courseId);
    }

    private void searchedOrThrowExc(int id, int courseId) {

        if (!courseRepository.existsById(courseId)) {
            throw new ItemNotFoundException("Курс с таким Id: " + courseId + " не найден");
        }

        if (!studentRepository.existsById(id)) {
            throw new ItemNotFoundException("Студент с таким Id: " + id + " не найден");
        }
    }


}


