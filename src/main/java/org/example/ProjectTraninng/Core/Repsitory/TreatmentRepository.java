package org.example.ProjectTraninng.Core.Repsitory;

import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    @Override
    Optional<Treatment> findById(Long aLong);

    @Query("SELECT t FROM Treatment t WHERE t.patient.id = :patientId")
    List<Treatment> findAllByPatientId(@Param("patientId") Long patientId);
}
