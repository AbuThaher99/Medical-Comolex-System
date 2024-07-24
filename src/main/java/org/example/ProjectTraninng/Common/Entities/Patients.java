package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName", nullable = false)
    @NotNull(message = "First name is required")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotNull(message = "Last name is required")
    private String lastName;

    @Column(name = "age", nullable = false)
    @NotNull(message = "Age is required")
    private Integer age;

    @Column(name = "address", nullable = false)
    @NotNull(message = "Address is required")
    private String address;

    @Column(name = "phone")
    @Pattern(regexp = "^(\\+\\d{1,3}[-]?)?\\d{10}$", message = "Invalid phone number")
    private String phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "patientId")
    private Set<Treatment> treatments; // done


    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdDate;
}
