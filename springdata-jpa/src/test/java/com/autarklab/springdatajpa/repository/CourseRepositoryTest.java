package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Course;
import com.autarklab.springdatajpa.enity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void print_courses(){
        List<Course> courses =
                courseRepository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    void save_course_with_teacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Laura")
                .lastName("Reina")
                .build();

        Course course = Course.builder()
                .title("Excel")
                .credit(3)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

}