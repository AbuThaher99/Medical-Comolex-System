package org.example.ProjectTraninng.Common.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    @NotNull(message = "Patient is required")
    @JsonBackReference("patient-notification")
    private Patients patient;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    @NotNull(message = "Message cannot be blank")
    private String message;

    @Column(name = "isRead", nullable = false)
    @Builder.Default
    private boolean isRead = false;

}
