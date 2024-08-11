package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.Donor;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;
import org.example.ProjectTraninng.Common.Enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    @Query("select d from Donor d where d.isDeleted = false and (:search IS NULL OR :search = '' OR d.name like %:search% or d.phone like %:search%) and " +
            "(:bloodType is null or d.bloodType = :bloodType) and " +
            "(:donorIds is null or d.id in :donorIds) and " +
            "(:gender is null or d.gender =:gender)")
    Page<Donor> findAll(Pageable pageable, @Param("search") String search, @Param("bloodType") BloodTypes bloodType, @Param("donorIds")List<Long> donorIds , @Param("gender") Gender gender);
}
