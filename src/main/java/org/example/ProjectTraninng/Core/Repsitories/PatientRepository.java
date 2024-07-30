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

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long> {
    Optional<Patients> findByFirstName(String firstName);


    @Query("SELECT p FROM Patients p WHERE " +
            "(:search IS NULL OR :search = '' OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.address) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.phone) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:doctorIds IS NULL OR p.id IN (SELECT t.patient.id FROM Treatment t WHERE t.doctor.id IN :doctorIds))")
    Page<Patients> findAll(Pageable pageable, @Param("search") String search, @Param("doctorIds") List<Long> doctorIds);


}
