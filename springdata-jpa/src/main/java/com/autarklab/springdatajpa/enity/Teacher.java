package com.autarklab.springdatajpa.enity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_teacher")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString()
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1,
            initialValue = 9000
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
    private Long teacherId;
    private String firstName;
    private String lastName;

    // one teacher can teach many courses
    /*@OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id_fk",
            referencedColumnName = "teacherId"
    )
    private List<Course> courses;*/
}
