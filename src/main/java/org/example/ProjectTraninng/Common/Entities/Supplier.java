package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.ProjectTraninng.Common.Enums.CompanyNames;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")

public class Supplier extends BaseEntity {


    @Column(name = "name", nullable = false)
    @NotNull(message = "Name is required")
    private String name;


    @Column(name = "companyName", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Company Name is required")
    private CompanyNames companyName;

    @Column(name = "phone", nullable = false)
    @NotNull(message = "Phone is required")
    private String phone;

    @Column(name = "address", nullable = false)
    @NotNull(message = "Address is required")
    private String address;

    @Column(name = "isDeleted" , nullable = false )
    @JsonIgnore
    private boolean isDeleted;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId", referencedColumnName = "id")
    @JsonManagedReference("supplier-medicine")
    private List<Medicine> medicines;
}
