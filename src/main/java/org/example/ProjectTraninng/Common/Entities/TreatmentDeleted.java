package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "treatments_deleted")
public class TreatmentDeleted extends BaseEntity {

    @Column(name = "treatmentDeletedId" , nullable = false , updatable = false)
    private Long treatmentDeletedId;


    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    @JsonBackReference("doctor-treatment")
    private Doctor doctor;


    @Column(name = "treatmentDate", updatable = false , nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date treatmentDate;

    @Column(name = "diseaseDescription", nullable = false, length = 255)
    private String diseaseDescription;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
