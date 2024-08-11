package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.Donation;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("select d from Donation d where d.isDeleted = false and d.donor.isDeleted = false and (:search IS NULL OR :search = '' OR d.donor.name like %:search% or d.donor.phone like %:search%) and " +
            "(:bloodType is null or d.donor.bloodType = :bloodType) and " +
            "(:donorIds is null or d.donor.id in :donorIds) and " +
            "(:quantity is null or d.quantity = :quantity)")
    Page<Donation> findAll(Pageable pageable , @Param("search") String search, @Param("bloodType") BloodTypes bloodType, @Param("donorIds") List<Long> donorIds, @Param("quantity") Integer quantity);
}
