package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.DeletedPatientMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedPatientMedicineRepository extends JpaRepository<DeletedPatientMedicine, Long> {
}
