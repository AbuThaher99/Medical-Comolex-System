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
@Table(name = "patient_medicine")
public class PatientMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "treatmentId", nullable = false)
    private Long treatmentId;

    @Column(name = "medicineId", nullable = false)
    private Long medicineId;

    @ManyToOne
    @JoinColumn(name = "treatmentId", insertable = false, updatable = false)
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "medicineId", insertable = false, updatable = false)
    private Medicine medicine;
}
