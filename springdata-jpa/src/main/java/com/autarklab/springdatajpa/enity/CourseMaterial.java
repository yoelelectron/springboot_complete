package com.autarklab.springdatajpa.enity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_course_material")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;

    // relationship with Course entity
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "course_id_fk",
            referencedColumnName = "courseId"
    )
    private Course course;

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "courseMaterialId=" + courseMaterialId +
                ", url='" + url + '\'' +
                '}';
    }
}
