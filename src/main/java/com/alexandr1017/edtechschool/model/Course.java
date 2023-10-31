package com.alexandr1017.edtechschool.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

import java.util.Objects;


@Entity
@Table(name = "course")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int duration;
    private int price;

    @Column(name = "creating_date")
    private LocalDate creatingDate;


    @JoinColumn(name = "teacher_id")
    @ManyToOne
    private Teacher teacherId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_student",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    public Course() {
    }

    public static Course toEntity(Object[] row) {
        Course course = new Course();
        course.setId((Integer) row[0]);
        course.setName((String) row[3]);
        course.setDuration((Integer) row[2]);
        course.setPrice((Integer) row[4]);
        course.setCreatingDate(((Date) row[1]).toLocalDate());
        return course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(LocalDate creatingDate) {
        this.creatingDate = creatingDate;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && duration == course.duration && price == course.price && Objects.equals(name, course.name) && Objects.equals(creatingDate, course.creatingDate) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, price, creatingDate, students);
    }

    @Override
    public String toString() {
        return "Course{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", duration=" + duration +
               ", price=" + price +
               ", creatingDate=" + creatingDate +
               ", students=" + students +
               '}';
    }
}
