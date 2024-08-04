package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Department extends BaseEntity {


    @Column(name = "name", nullable = false)
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "isDeleted" , nullable = false )
    @JsonIgnore
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "headId")
    @JsonBackReference("headUser")
    private User headId;

//    @ManyToOne
//    @JoinColumn(name = "fk_emp_id")
//    private Employee employee;
    //@JsonBackReference

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "secretaryId")
    @JsonBackReference("secretaryUser")
    private User secretaryId;

}