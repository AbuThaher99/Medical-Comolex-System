package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "patientsBlood")
public class PatientsBlood extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId", nullable = false)
    @JsonBackReference("patientsBloodTaken")
    private Patients patients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientsBloodId" , nullable = false)
    @JsonBackReference("patientsBloodTypetaken")
    private BloodType patientsBlood;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Quantity is required")
    private int quantity;

    @Column(name = "isDeleted")
    @JsonIgnore
    @Builder.Default
    private boolean isDeleted = false;
}
