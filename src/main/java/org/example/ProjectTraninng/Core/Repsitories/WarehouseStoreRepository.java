package org.example.ProjectTraninng.Core.Repsitories;

import jakarta.transaction.Transactional;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WarehouseStoreRepository extends JpaRepository<WarehouseStore, Long> {
  @Query("select w from WarehouseStore w where w.medicine.id = :medicineId and w.isDeleted = false")
    Optional<WarehouseStore> findById(@Param("medicineId") Long aLong);
   // @Query("select w from WarehouseStore w where w.medicine.id = :medicineId")
    @Query("select w from WarehouseStore w where w.medicine.id = :medicineId and w.isDeleted = false")
    boolean existsByMedicineId(@Param("medicineId") Long medicineId);
    @Query("select w from WarehouseStore w where w.medicine.id = :medicineId and w.isDeleted = false")
    WarehouseStore findByMedicineId(@Param("medicineId") Long medicineId);
    @Query("select w from WarehouseStore w where w.isDeleted = false")
    Page<WarehouseStore> findAll(Pageable pageable);

}
