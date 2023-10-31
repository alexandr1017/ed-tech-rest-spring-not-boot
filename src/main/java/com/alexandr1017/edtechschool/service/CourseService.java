package com.alexandr1017.edtechschool.service;


import com.alexandr1017.edtechschool.dto.CourseDto;

import java.util.List;

public interface CourseService {


    List<CourseDto> findAll();

    CourseDto getCourseById(int courseId);

    void saveCourse(CourseDto courseDto);


    void deleteCourseById(int courseId);

}
