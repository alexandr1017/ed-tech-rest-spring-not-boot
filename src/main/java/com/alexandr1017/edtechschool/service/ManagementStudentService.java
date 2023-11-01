package com.alexandr1017.edtechschool.service;

public interface ManagementStudentService {
    void addStudentToCourse(int studentId, int courseId);
    void removeStudentFromCourse(int studentId, int courseId);
}
