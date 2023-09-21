package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Course;
import com.autarklab.springdatajpa.enity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    void save_course_material() {

        Course course = Course.builder()
                .title("physics")
                .credit(4)
                .build();

        CourseMaterial material =
                CourseMaterial.builder()
                        .url("www.santoalla.com")
                        .course(course)
                        .build();
        repository.save(material);
    }

    @Test
    void bringAllCoursesMaterial(){
        List<CourseMaterial> courseMaterials =
                repository.findAll();

        System.out.println("lists:" + courseMaterials);
    }
}