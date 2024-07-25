package org.example.ProjectTraninng.Common.Entities;

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
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "The Buy Price is required")
    @Column(name = "buyPrice", nullable = false)
    private Double buyPrice;

    @NotNull(message = "The Purchase Price is required")
    @Column(name = "purchasePrice", nullable = false)
    private Double purchasePrice;

    @NotNull(message = "The Expiration Date is required")
    @Column(name = "expirationDate", nullable = false)
    private Date expirationDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "medicineId", referencedColumnName = "id")
    @JsonManagedReference("medicine-patientMedicine")
    private List<PatientMedicine> patientMedicines;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "medicineId", referencedColumnName = "id")
    @JsonManagedReference("medicine-warehouseStore")
    private List<WarehouseStore> warehouseStores;

    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdDate;
}
