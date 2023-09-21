package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByLastNameOrderByFirstNameDesc(String lastName);

    public List<Student> findByFirstNameContainingIgnoreCaseOrderByFirstNameAsc(String firstName);
}
