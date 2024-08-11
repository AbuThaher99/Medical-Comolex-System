package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;
import org.example.ProjectTraninng.Common.Enums.Gender;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donors")
public class Donor extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "bloodType", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "bloodType is required")
    private BloodTypes bloodType;

    @Column(name="gender" , nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "gender is required")
    private Gender gender;

    @Column(name = "isDeleted")
    @JsonIgnore
    @Builder.Default
    private boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinColumn(name = "donorId" , referencedColumnName = "id")
    @JsonManagedReference("donor-donation")
    private List<Donation> donations;

}
