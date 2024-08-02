package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_medicine")
public class PatientMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price is required")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "treatmentId")
    @NotNull(message = "Treatment is required")
    @JsonBackReference("treatment-patientMedicine")
    private Treatment treatment;
    @ManyToOne
    @JoinColumn(name = "medicineId", nullable = false)
    @NotNull(message = "Medicine is required")
    @JsonBackReference("medicine-patientMedicine")
    private Medicine medicine;
}
