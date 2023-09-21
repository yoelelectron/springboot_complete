package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Course;
import com.autarklab.springdatajpa.enity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    void findAllPagination(){
        Pageable pageableFirst =
                PageRequest.of(0,3);
        Pageable pageableSeconf =
                PageRequest.of(1,2);

        List<Course> courses =
                courseRepository.findAll(pageableFirst)
                        .getContent();

        long totalElements =
                courseRepository.findAll(pageableFirst)
                                .getTotalElements();

        long totalPages =
                courseRepository.findAll(pageableFirst)
                        .getTotalPages();

        System.out.println("totalPages = " + totalPages);

        System.out.println("totalElements = " + totalElements);

        System.out.println("courses = " + courses );
    }

    @Test
    void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(0,2, Sort.by("title"));

        Pageable sortByCreditDesc =
                PageRequest.of(0,2, Sort.by("credit").descending());


        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    void printfindByTitleContaining() {
        Pageable firstPageTenRecords=
                PageRequest.of(0,10);

        List<Course> courses =
                courseRepository.findByTitleContainingIgnoreCase("X", firstPageTenRecords)
                        .getContent();

        System.out.println("courses = " + courses);
    }

}