package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
