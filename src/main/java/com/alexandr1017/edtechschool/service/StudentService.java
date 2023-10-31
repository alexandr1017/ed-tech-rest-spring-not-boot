package com.alexandr1017.edtechschool.service;

import com.alexandr1017.edtechschool.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();

    StudentDto getStudentById(int studentId);

    void saveStudent(StudentDto studentDto);

    void deleteStudentById(int studentId);
}
