package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Guardian;
import com.autarklab.springdatajpa.enity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){

        Guardian guardian = Guardian.builder()
                        .email("y2@mail.com")
                        .name("yohan")
                        .mobile("524564")
                        .build();

        Student student = Student.builder()
                .emailId("carl2@mail.com")
                .firstName("Carlos")
                .lastName("Gonzalez")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    void printAllStudent() {
        List<Student> students = studentRepository.findAll();

        System.out.println(students);
    }

    @Test
    public void printStudentByLastName() {

        List<Student> students =
                studentRepository.findByLastNameOrderByFirstNameDesc("Gonzalez");

        System.out.println("nana" + students);
    }

    @Test
    public void printStudentByNameContaining() {

        List<Student> students =
                studentRepository.findByFirstNameContainingIgnoreCaseOrderByFirstNameAsc("oS");

        System.out.println("nana" + students);
    }

}