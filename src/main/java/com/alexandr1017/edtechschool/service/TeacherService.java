package com.alexandr1017.edtechschool.service;

import com.alexandr1017.edtechschool.dto.CourseDto;
import com.alexandr1017.edtechschool.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> findAll();
    TeacherDto getTeacherById(int teacherId);

    void saveTeacher(TeacherDto teacherDto);

    void deleteTeacherById(int teacherId);

}
