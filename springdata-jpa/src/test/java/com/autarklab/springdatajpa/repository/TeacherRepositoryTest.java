package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Course;
import com.autarklab.springdatajpa.enity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void save_teacher(){

        Course courseMec = Course.builder()
                .title("Mechanics")
                .credit(6)
                .build();

        Course courseMath = Course.builder()
                .title("maths")
                .credit(5)
                .build();


        Teacher teacher = Teacher.builder()
                .firstName("Leidy")
                .lastName("Gonzalez")
                .courses(List.of(courseMath, courseMec))
                .build();

        teacherRepository.save(teacher);
    }
}