package org.example.ProjectTraninng.Core.Repsitories;

import jakarta.transaction.Transactional;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long> {
    Optional<Patients> findByFirstName(String firstName);

    @Query("SELECT p FROM Patients p JOIN p.treatments t WHERE (p.firstName LIKE %:search% OR p.lastName LIKE %:search% OR p.address LIKE %:search% OR p.phone LIKE %:search%) andgi t.doctor.id = :doctorId")
    Page<Patients> findAll(Pageable pageable , @Param("search") String search , @Param("doctorId") Long doctorId);
}
