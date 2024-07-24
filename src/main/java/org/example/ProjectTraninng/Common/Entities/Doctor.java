package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ProjectTraninng.Common.Enums.Specialization;

import java.time.LocalTime;
import java.util.Set;

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
    @NotNull(message = "Specialization cannot be Null")
    private Specialization specialization;

    @Column(name = "beginTime", nullable = false)
    @NotNull(message = "Begin time cannot be Null")
    private LocalTime beginTime; // Changed to LocalTime

    @Column(name = "endTime", nullable = false)
    @NotNull(message = "End time cannot be Null")
    private LocalTime endTime; // Changed to LocalTime

    @OneToOne
    @JsonManagedReference(value = "userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "doctorId")
    @JsonIgnore
    private Set<Treatment> treatments; // done
}
