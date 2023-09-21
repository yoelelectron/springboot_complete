package com.autarklab.springdatajpa.repository;

import com.autarklab.springdatajpa.enity.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Query Methods
    public List<Student> findByLastNameOrderByFirstNameDesc(String lastName);

    public List<Student> findByFirstNameContainingIgnoreCaseOrderByFirstNameAsc(String firstName);

    //JpaQuery Annotation (JPL Queries)
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentsByEmailAddress(String emailId);

    @Query("select s from Student s where s.guardian.email = ?1")
    List<Student> getStudentsByGuardianEmailAddress(String email);

    // Native Queries
    @Query(
            value = "SELECT * FROM t_student s where s.email_address = ?1 and s.guardian_email = ?2",
            nativeQuery = true
    )
    Student getStudentFullNameByEmailAddressAndGuardianEmail(String email, String GuardianEmail);

    // Query named Params
    @Query(
            value = "SELECT * FROM t_student s where s.email_address = :studentEmail",
            nativeQuery = true
    )
    Student getStudentFullNameByEmailAddress(@Param("studentEmail") String email);

    /*
        ** @ Transactional and Modifying
     */

    @Modifying
    @Transactional
    @Query(
            value = "update t_student set last_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentLastNameByEmailId(String lastName, String emailId);

}
