package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "Name cannot be null")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "headDepartment")
    @JsonBackReference(value = "headDepartment")
    private User headDepartment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "secretaryId")
    @JsonBackReference(value = "secretaryDepartment")
    private User secretary;

    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdDate;
}