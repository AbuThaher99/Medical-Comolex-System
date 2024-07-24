package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Entities.Patients;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId", nullable = false)
    @JsonBackReference(value = "patientId")
    @NotNull(message = "Patient is required")
    private Patients patient; // done

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId", nullable = false)
    @JsonBackReference(value = "doctorId")
    @NotNull(message = "Doctor is required")
    private Doctor doctor; //done

    @OneToMany(mappedBy = "treatment")
    @JsonManagedReference(value = "treatmentId")
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
