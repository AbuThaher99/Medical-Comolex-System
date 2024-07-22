package org.example.ProjectTraninng.Core.Repsitory;

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
    boolean existsByMedicineId(Long medicineId);
    @Query("select w from WarehouseStore w where w.medicineId = :medicineId")
    WarehouseStore findByMedicineId(@Param("medicineId") Long medicineId);
    @Modifying
    @Transactional
    @Query("delete FROM WarehouseStore w where w.medicineId = :medicineId")
    void deleteByMedicineId(@Param("medicineId") Long medicineId);
}
