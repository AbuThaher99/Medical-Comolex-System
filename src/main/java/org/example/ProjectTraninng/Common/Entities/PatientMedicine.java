package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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


    @ManyToOne
    @JoinColumn(name = "treatmentId",  nullable = false)
    @JsonBackReference(value = "treatmentId")
    @NotNull(message = "Treatment is required")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "medicineId" ,nullable = false)
    @JsonBackReference(value = "medicineId")
    @NotNull(message = "Medicine is required")
    private Medicine medicine;

}
