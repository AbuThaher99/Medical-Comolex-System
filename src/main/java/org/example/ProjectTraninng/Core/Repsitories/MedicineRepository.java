package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    @Query("select m from Medicine m where m.name = :medicineName and m.isDeleted = false")
    Optional<Medicine> findByName(@Param("medicineName") String medicineName);
    @Query("select m from Medicine m where m.isDeleted = false")
    Page<Medicine> findAll( Pageable pageable);

    @Query("select m from Medicine m where m.id = :id and m.isDeleted = false")
    Optional<Medicine> findById(@Param("id") Long id);

    @Query("select m from Medicine m where m.isDeleted = false")
    List<Medicine> findAll();

    @Query("select m FROM Medicine m where m.isDeleted = false and " +
            "(:search IS NULL or :search = '' or " +
            "lower(m.name) LIKE lower(concat('%', :search, '%')) or " +
            "cast(m.buyPrice AS string) LIKE concat('%', :search, '%') or " +
            "cast(m.purchasePrice AS string) LIKE concat('%', :search, '%') or " +
            "cast(m.expirationDate AS string) LIKE concat('%', :search, '%'))")
    Page<Medicine> findAll(Pageable pageable, @Param("search") String search);

    @Query("select m from Medicine m where m.name = :name and m.supplier.id = :supplierId and m.isDeleted = false")
    Optional<Medicine> findByNameAndSupplierId(@Param("name") String name,@Param("supplierId") Long supplierId);

}
