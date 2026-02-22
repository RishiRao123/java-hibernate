package org.raoamigos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "admission_date", updatable = false)
    private LocalDateTime admissionDate;

    @Override
    public String toString() {
        return "Student{" +
                "admissionDate=" + admissionDate +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
