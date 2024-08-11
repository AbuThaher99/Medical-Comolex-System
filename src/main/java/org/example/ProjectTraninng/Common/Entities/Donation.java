package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "donorId" , nullable = false)
    @NotNull(message = "Donor is required")
    @JsonBackReference("donor-donation")
    private Donor donor;


    @Column(name = "quantity", nullable = false)
    private int quantity; // quantity of blood in units

    @Column(name = "donationDate", updatable = false , nullable = false )
    @NotNull(message = "Donation date is required")
    private Date donationDate;

    @Column(name = "isDeleted")
    @JsonIgnore
    @Builder.Default
    private boolean isDeleted = false;


    @ManyToOne
    @JoinColumn(name = "bloodTypeId" , nullable = false)
    @NotNull(message = "Blood Type is required")
    @JsonBackReference("bloodTypeDonation")
    private BloodType bloodType;


}
