package org.example.ProjectTraninng.Common.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deleted_patient_medicine")
public class DeletedPatientMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "treatmentDeletedId")
    private TreatmentDeleted treatmentDeleted;

    @ManyToOne
    @JoinColumn(name = "medicineId", nullable = false)
    private Medicine medicine;
}
