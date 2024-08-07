package org.example.ProjectTraninng.Core.Repsitories;
import org.example.ProjectTraninng.Common.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.patient.id = :patientId AND n.isRead = false")
    List<Notification> findByPatientIdAndIsReadFalse(@Param("patientId") Long patientId);
    @Query("SELECT n FROM Notification n WHERE n.patient.id = :patientId and n.message = :message")
    Notification findByPatientIdAndMessage(Long patientId, String message);
}
