package com.alexandr1017.edtechschool.controller;

import com.alexandr1017.edtechschool.service.ManagementStudentService;
import com.alexandr1017.edtechschool.service.ManagementTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management")
public class ManagementController {
    private ManagementStudentService managementStudentService;
    private ManagementTeacherService managementTeacherService;

    @Autowired
    public ManagementController(ManagementStudentService managementStudentService, ManagementTeacherService managementTeacherService) {
        this.managementStudentService = managementStudentService;
        this.managementTeacherService = managementTeacherService;
    }

    @PostMapping("/student")
    public void addStudentToCourse(@RequestParam int studentId, @RequestParam int courseId) {
        managementStudentService.addStudentToCourse(studentId, courseId);
    }

    @DeleteMapping("/student")
    public void removeStudentFromCourse(@RequestParam int studentId, @RequestParam int courseId) {
        managementStudentService.removeStudentFromCourse(studentId, courseId);
    }

    @PostMapping("/teacher")
    public void addTeacherToCourse(@RequestParam int teacherId, @RequestParam int courseId) {
        managementTeacherService.addTeacherToCourse(teacherId, courseId);
    }

    @PatchMapping("/teacher")
    public void replaceTeacherOnCourse(@RequestParam int teacherId, @RequestParam int courseId) {
        managementTeacherService.replaceTeacherOnCourse(teacherId, courseId);
    }

}
