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


    @Query("SELECT p , p.user FROM Patients p WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "p.user.firstName like %:search% or p.user.lastName like %:search% or p.user.address like %:search% or p.user.phone like %:search%) and" +
            "(:doctorIds IS NULL OR p.id IN (SELECT t.patient.id FROM Treatment t WHERE t.doctor.id IN :doctorIds))")
    Page<Patients> findAll(Pageable pageable, @Param("search") String search , @Param("doctorIds") List<Long> doctorIds);
//    @Query("select  p from Patients p where (:search IS NULL OR :search = '' OR " +
//            "p.firstName like %:search% or p.lastName like %:search% or p.address like %:search% or p.phone like %:search%) and" +
//            "(:doctorIds IS NULL OR p.id IN (SELECT t.patient.id FROM Treatment t WHERE t.doctor.id IN :doctorIds))")
//    Page<Patients> findAll(Pageable pageable, @Param("search") String search, @Param("doctorIds") List<Long> doctorIds);
    @Query("SELECT p FROM Patients p WHERE p.user.email = :email and p.user.isDeleted = false")
    Optional<Patients> findByUserEmail(@Param("email") String email);
}
