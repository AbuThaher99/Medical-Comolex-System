package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback extends BaseEntity {

    @Column(name = "comment", nullable = false)
    @NotNull(message = "Comment cannot be null")
    private String comment;

    @Column(name = "rating", nullable = false)
    @NotNull(message = "Rating cannot be null")
    private Integer rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId", nullable = false)
    @JsonBackReference("doctor-feedback")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    @JsonBackReference("patient-feedback")
    private Patients patient;
}
