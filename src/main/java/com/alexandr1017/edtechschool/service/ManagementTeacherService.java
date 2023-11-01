package com.alexandr1017.edtechschool.service;

public interface ManagementTeacherService {
    void addTeacherToCourse(int teacherId, int courseId);
    void replaceTeacherOnCourse(int teacherId, int courseId);
}
