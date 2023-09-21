package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Course;
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

}