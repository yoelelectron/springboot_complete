package com.autarklab.springdatajpa.enity;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name", nullable = false)
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile", nullable = false)
        )
})
public class Guardian {

    private String name;
    private String email;
    private String mobile;

    @Override
    public String toString() {
        return "Guardian{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
