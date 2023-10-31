package com.alexandr1017.edtechschool.controller;

import com.alexandr1017.edtechschool.dto.CourseDto;
import com.alexandr1017.edtechschool.dto.StudentDto;
import com.alexandr1017.edtechschool.service.CourseService;
import com.alexandr1017.edtechschool.service.StudentService;
import com.alexandr1017.edtechschool.service.impl.CourseServiceImpl;
import com.alexandr1017.edtechschool.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

        private StudentService studentService;

        @Autowired
        public StudentController(StudentServiceImpl studentService) {
            this.studentService = studentService;
        }

        @GetMapping("/")
        @ResponseBody
        public List<StudentDto> showCourses() {
            return studentService.findAll();
        }
        @GetMapping("/{id}")
        @ResponseBody
        public StudentDto showCourse(@PathVariable int id) {
            return studentService.getStudentById(id);
        }

        @PostMapping("/")
        public void addCourse(@RequestBody StudentDto student) {
            studentService.saveStudent(student);
        }

        @PutMapping("/")
        public void updateCourse(@RequestBody StudentDto student) {
            studentService.saveStudent(student);
        }

        @DeleteMapping("/{id}")
        public void deleteCourse(@PathVariable int id) {
            studentService.deleteStudentById(id);
        }
    }


