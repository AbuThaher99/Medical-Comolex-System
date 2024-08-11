package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.PatientsBlood;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientsBloodRepository extends JpaRepository<PatientsBlood, Long> {
    @Query("select pb from PatientsBlood pb where pb.isDeleted = false and  (:search IS NULL OR :search = '' OR pb.patients.user.firstName like %:search% " +
            "or pb.patients.user.phone like %:search% or pb.patients.user.lastName like  %:search% ) and " +
            "(:bloodType is null or pb.patientsBlood.type = :bloodType) and " +
            "(:patientIds is null or pb.patients.id in :patientIds) and " +
            "(:quantity is null or pb.quantity = :quantity)")
    Page<PatientsBlood> findAll(Pageable pageable, @Param("search") String search, @Param("patientIds") List<Long> patientIds, @Param("bloodType")BloodTypes bloodType, @Param("patientIds") Integer quantity);
}
