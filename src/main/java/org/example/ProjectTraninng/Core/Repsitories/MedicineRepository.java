package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    @Query("select m from Medicine m where m.name = :medicineName and m.isDeleted = false")
    Optional<Medicine> findByName(@Param("medicineName") String medicineName);
    @Query("select m from Medicine m where m.isDeleted = false")
    Page<Medicine> findAll( Pageable pageable);

    @Query("select m from Medicine m where m.id = :id and m.isDeleted = false")
    Optional<Medicine> findById(@Param("id") Long id);
}
