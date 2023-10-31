package com.alexandr1017.edtechschool.service.impl;

import com.alexandr1017.edtechschool.dao.TeacherRepository;
import com.alexandr1017.edtechschool.dto.CourseDto;

import com.alexandr1017.edtechschool.dto.TeacherDto;
import com.alexandr1017.edtechschool.exception.ItemNotFoundException;
import com.alexandr1017.edtechschool.model.Course;

import com.alexandr1017.edtechschool.model.Teacher;
import com.alexandr1017.edtechschool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> findAll() {
        return teacherRepository.findAll().stream().map(teacher -> {
                    List<CourseDto> courseDtos
                            = teacherRepository
                            .findCoursesForTeacher(teacher.getId())
                            .stream()
                            .map(Course::toEntity)
                            .map(CourseDto::toDto)
                            .collect(Collectors.toList());

                    TeacherDto dto = TeacherDto.toDto(teacher);
                    dto.setCourses(courseDtos);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto getTeacherById(int teacherId) {
        return teacherRepository.findById(teacherId).map(
                teacher -> {
                    List<CourseDto> courseDtos = teacherRepository.findCoursesForTeacher(teacher.getId())
                            .stream().map(Course::toEntity)
                            .map(CourseDto::toDto)
                            .collect(Collectors.toList());

                    return new TeacherDto(
                            teacher.getId(),
                            teacher.getName(),
                            teacher.getAge(),
                            courseDtos);
                }).orElseThrow(() -> new ItemNotFoundException("Учитель с таким Id: " + teacherId + " не найден"));
    }


    @Override
    public void saveTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setName(teacherDto.getName());
        teacher.setAge(teacherDto.getAge());
        teacher.setHireDate(LocalDate.now());

        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(int teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new ItemNotFoundException("Учитель с таким Id: " + teacherId + " не найден");
        }
        teacherRepository.deleteById(teacherId);
    }
}
