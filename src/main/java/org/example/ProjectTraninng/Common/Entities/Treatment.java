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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    @NotNull(message = "Patient is required")
    @JsonBackReference("patient-treatment")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    @NotNull(message = "Doctor is required")
    @JsonBackReference("doctor-treatment")
    private Doctor doctorId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "treatmentId", referencedColumnName = "id")
    @JsonManagedReference("treatment-patientMedicine")
    private List<PatientMedicine> patientMedicines;

    @Column(name = "treatmentDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date treatmentDate;

    @Column(name = "diseaseDescription", nullable = false, length = 255)
    @NotNull(message = "Disease description is required")
    private String diseaseDescription;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
