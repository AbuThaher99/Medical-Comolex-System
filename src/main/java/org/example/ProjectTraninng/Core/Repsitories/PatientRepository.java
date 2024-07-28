package org.example.ProjectTraninng.Core.Repsitories;

import jakarta.transaction.Transactional;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long> {
    Optional<Patients> findByFirstName(String firstName);

    @Modifying
    @Transactional
    @Query("delete FROM Patients p where p.id = :id")
    void deleteByPatientId(@Param("id") Long id);
}
