package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bloodType")
public class BloodType extends BaseEntity {

    @Column(name = "type", nullable = false , unique = true)
    @Enumerated(EnumType.STRING)
    private BloodTypes type;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Quantity is required")
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinColumn(name = "bloodTypeId" , referencedColumnName = "id")
    @JsonManagedReference("bloodTypeDonation")
    private List<Donation> donations;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinColumn(name = "bloodTypeId" , referencedColumnName = "id")
    @JsonManagedReference("patient-bloodType")
    private List<Patients> patients;


    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinColumn(name = "patientsBloodId" , referencedColumnName = "id")
    @JsonManagedReference("patientsBloodTypetaken")
    private List<PatientsBlood> patientsBloods;

}
