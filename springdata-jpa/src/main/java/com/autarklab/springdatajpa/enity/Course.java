package com.autarklab.springdatajpa.enity;

import lombok.*;

import javax.persistence.*;

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


/*    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                '}';
    }*/
}
