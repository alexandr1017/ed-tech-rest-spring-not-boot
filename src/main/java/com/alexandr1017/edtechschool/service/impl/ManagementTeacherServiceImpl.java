package com.alexandr1017.edtechschool.service.impl;

import com.alexandr1017.edtechschool.dao.CourseRepository;
import com.alexandr1017.edtechschool.dao.ManagementTeacherRepository;
import com.alexandr1017.edtechschool.dao.TeacherRepository;
import com.alexandr1017.edtechschool.exception.ItemNotFoundException;
import com.alexandr1017.edtechschool.service.ManagementTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementTeacherServiceImpl implements ManagementTeacherService {


    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    private ManagementTeacherRepository managementTeacherRepository;


    @Autowired
    public ManagementTeacherServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, ManagementTeacherRepository managementTeacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.managementTeacherRepository = managementTeacherRepository;
    }


    @Override
    public void addTeacherToCourse(int teacherId, int courseId) {
        searchedOrThrowExc(teacherId, courseId);
        managementTeacherRepository.addTeacherToCourse(teacherId, courseId);
    }

    @Override
    public void replaceTeacherOnCourse(int teacherId, int courseId) {
        searchedOrThrowExc(teacherId, courseId);
        managementTeacherRepository.replaceTeacherOnCourse(teacherId, courseId);
    }

    private void searchedOrThrowExc(int id, int courseId) {

        if (!courseRepository.existsById(courseId)) {
            throw new ItemNotFoundException("Курс с таким Id: " + courseId + " не найден");
        }

        if (!teacherRepository.existsById(id)) {
            throw new ItemNotFoundException("Учитель с таким Id: " + id + " не найден");
        }
    }

}
