package org.example.ProjectTraninng.Core.Repsitory;

import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String medicineName);


}
