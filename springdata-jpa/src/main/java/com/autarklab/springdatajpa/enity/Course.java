package com.autarklab.springdatajpa.enity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString()
// @ToString(exclude = "course") same as bellow with method declaration
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Long courseId;
    private String title;
    private Integer credit;

    // Bidirectional relationship
    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id_fk",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    // Many Students may take many Courses and vice-versa (ManyToMany includes a relation table @JoinTable)
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "t_students_courses_map",
            joinColumns = @JoinColumn(
                    name = "course_id_fk",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id_fk",
                    referencedColumnName = "studentId"
            )

    )
    private List<Student> students;

    public void addStudents(Student student){
        if (students == null ){
            students = new ArrayList<>();
        }
        students.add(student);
    }


/*    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                '}';
    }*/
}
