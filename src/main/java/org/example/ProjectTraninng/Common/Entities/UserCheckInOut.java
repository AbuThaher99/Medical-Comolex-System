package org.example.ProjectTraninng.Common.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "checkInOut")
public class UserCheckInOut extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference("checkInOutUser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "madeByUserId", nullable = false)
    @JsonBackReference("checkInOutMadeByUser")
    private User madeByUser;

    @Column(name = "checkIn", nullable = false)
    private LocalDateTime checkIn;

    @Column(name = "checkOut")
    private LocalDateTime checkOut;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "hoursWorked")
    private Double hoursWorked;
}
