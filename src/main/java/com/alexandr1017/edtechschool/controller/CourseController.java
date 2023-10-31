package com.alexandr1017.edtechschool.controller;

import com.alexandr1017.edtechschool.dto.CourseDto;
import com.alexandr1017.edtechschool.model.Course;
import com.alexandr1017.edtechschool.service.CourseService;
import com.alexandr1017.edtechschool.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> showCourses() {
        return courseService.findAll();
    }
    @GetMapping("/{id}")
    @ResponseBody
    public CourseDto showCourse(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/")
    public void addCourse(@RequestBody CourseDto course) {
        courseService.saveCourse(course);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable int id, @RequestBody CourseDto course) {
        course.setId(id);
        courseService.saveCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourseById(id);
    }
}

