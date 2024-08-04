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
    @Builder.Default
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "headId")
    @JsonBackReference("headUser")
    private User headId;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "secretaryId")
    @JsonBackReference("secretaryUser")
    private User secretaryId;

}