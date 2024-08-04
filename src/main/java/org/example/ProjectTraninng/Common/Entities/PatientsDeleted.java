package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Table(name = "patients_deleted")

public class PatientsDeleted extends BaseEntity {


    @Column(name = "patientDeletedId")
    private Long patientDeletedId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY ,orphanRemoval = true)
    @JoinColumn(name = "patientId" , referencedColumnName = "patientDeletedId")
    @JsonManagedReference("patient-treatment-deleted")
    private List<Treatment> treatments;

}
