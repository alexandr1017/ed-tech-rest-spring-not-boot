package com.alexandr1017.edtechschool.controller;

import com.alexandr1017.edtechschool.dto.CourseDto;
import com.alexandr1017.edtechschool.dto.TeacherDto;
import com.alexandr1017.edtechschool.service.CourseService;
import com.alexandr1017.edtechschool.service.TeacherService;
import com.alexandr1017.edtechschool.service.impl.CourseServiceImpl;
import com.alexandr1017.edtechschool.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<TeacherDto> showCourses() {
        return teacherService.findAll();
    }
    @GetMapping("/{id}")
    @ResponseBody
    public TeacherDto showCourse(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping("/")
    public void addCourse(@RequestBody TeacherDto teacher) {
        teacherService.saveTeacher(teacher);
    }

    @PutMapping("/")
    public void updateCourse(@RequestBody TeacherDto teacher) {
        teacherService.saveTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        teacherService.deleteTeacherById(id);
    }
}

