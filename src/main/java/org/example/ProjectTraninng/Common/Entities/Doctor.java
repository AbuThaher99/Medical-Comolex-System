package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ProjectTraninng.Common.Enums.Specialization;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialization", nullable = false)
    private Specialization specialization;

    @Column(name = "beginTime", nullable = false)
    private LocalTime beginTime; // Changed to LocalTime

    @Column(name = "endTime", nullable = false)
    private LocalTime endTime; // Changed to LocalTime

    @OneToOne
    @JsonManagedReference(value = "userId")
    @JoinColumn(name = "userId")
    private User user;
}
