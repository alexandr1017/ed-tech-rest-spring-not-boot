package com.alexandr1017.edtechschool.service.impl;

import com.alexandr1017.edtechschool.dao.StudentRepository;
import com.alexandr1017.edtechschool.dto.CourseDto;
import com.alexandr1017.edtechschool.dto.StudentDto;
import com.alexandr1017.edtechschool.exception.ItemNotFoundException;
import com.alexandr1017.edtechschool.model.Course;
import com.alexandr1017.edtechschool.model.Student;
import com.alexandr1017.edtechschool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map((student -> {
                    List<Object[]> result = studentRepository.findCoursesForStudent(student.getId());
                    List<Course> courses = result.stream()
                            .map(Course::toEntity)
                            .collect(Collectors.toList());

                    List<CourseDto> courseDtos = courses.stream()
                            .map(CourseDto::toDto)
                            .collect(Collectors.toList());

                    StudentDto dto = StudentDto.toDto(student);
                    dto.setCourseDtos(courseDtos);
                    return dto;
                }))
                .collect(Collectors.toList());
    }


    @Override
    public StudentDto getStudentById(int studentId) {
        return studentRepository.findById(studentId).map(student -> {
            List<Object[]> result = studentRepository.findCoursesForStudent(student.getId());
            List<Course> courses = result.stream()
                    .map(Course::toEntity)
                    .collect(Collectors.toList());
            List<CourseDto> courseDtos = courses.stream()
                    .map(CourseDto::toDto)
                    .collect(Collectors.toList());
            return new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    courseDtos);
        }).orElseThrow(() -> new ItemNotFoundException("Студент с таким Id: " + studentId + " не найден"));
    }


    @Override
    public void saveStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setRegistrationDate(LocalDate.now());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(int studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ItemNotFoundException("Студент с таким Id: " + studentId + " не найден");
        }
        studentRepository.deleteById(studentId);
    }
}
