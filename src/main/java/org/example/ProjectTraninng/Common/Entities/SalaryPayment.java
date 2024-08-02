package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salaryPayments")
public class SalaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "salaryId", nullable = false)
    @NotNull(message = "User is required")
    @JsonBackReference("salaryUser")
    private User user;

    @Column(name = "paymentDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "salaryType", nullable = false)
    private String salaryType;
}
