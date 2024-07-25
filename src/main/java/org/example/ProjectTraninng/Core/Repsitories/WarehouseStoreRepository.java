package org.example.ProjectTraninng.Core.Repsitories;

import jakarta.transaction.Transactional;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WarehouseStoreRepository extends JpaRepository<WarehouseStore, Long> {
    @Override
    Optional<WarehouseStore> findById(Long aLong);
   // @Query("select w from WarehouseStore w where w.medicine.id = :medicineId")
    boolean existsByMedicineId( Long medicineId);
    @Query("select w from WarehouseStore w where w.medicine.id = :medicineId")
    WarehouseStore findByMedicineId(@Param("medicineId") Long medicineId);
    @Modifying
    @Transactional
    @Query("delete FROM WarehouseStore w where w.medicine.id = :medicineId")
    void deleteByMedicineId(@Param("medicineId") Long medicineId);
}
