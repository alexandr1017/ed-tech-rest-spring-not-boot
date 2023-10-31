package com.alexandr1017.edtechschool.service.impl;

import com.alexandr1017.edtechschool.dao.CourseRepository;
import com.alexandr1017.edtechschool.dto.CourseDto;
import com.alexandr1017.edtechschool.dto.StudentDto;
import com.alexandr1017.edtechschool.exception.ItemNotFoundException;
import com.alexandr1017.edtechschool.model.Course;
import com.alexandr1017.edtechschool.model.Student;
import com.alexandr1017.edtechschool.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    List<Object[]> result = courseRepository.findStudentsByCourse(course.getId());
                    List<Student> students = result.stream()
                            .map(Student::toEntity)
                            .collect(Collectors.toList());

                    List<StudentDto> studentDtos = students.stream()
                            .map(StudentDto::toDto)
                            .collect(Collectors.toList());

                    CourseDto dto = CourseDto.toDto(course);
                    dto.setStudentDtos(studentDtos);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(int courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    List<Object[]> result = courseRepository.findStudentsByCourse(course.getId());
                    List<Student> students = result.stream()
                            .map(Student::toEntity)
                            .collect(Collectors.toList());
                    List<StudentDto> studentDtos = students.stream().map(StudentDto::toDto).collect(Collectors.toList());
                    return new CourseDto(
                            course.getId(),
                            course.getName(),
                            course.getDuration(),
                            course.getPrice(),
                            studentDtos);
                }).orElseThrow(() -> new ItemNotFoundException("Курс с таким Id: " + courseId + " не найден"));
    }

    @Override
    public void saveCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDuration(courseDto.getDuration());
        course.setPrice(courseDto.getPrice());
        course.setCreatingDate(LocalDate.now());
        courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(int courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ItemNotFoundException("Курс с таким Id: " + courseId + " не найден");
        }
        courseRepository.deleteById(courseId);
    }
}
