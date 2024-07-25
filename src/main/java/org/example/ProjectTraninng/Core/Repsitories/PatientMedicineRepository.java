package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientMedicineRepository extends JpaRepository<PatientMedicine, Long> {

    @Query("select pm from PatientMedicine pm where pm.treatment.id = :treatmentId")
    List<PatientMedicine> findAllByTreatmentId(@Param("treatmentId") Long treatmentId);

    @Query("select pm from PatientMedicine pm where pm.medicine.id = :medicineId")
    List<PatientMedicine> findAllByMedicineId(@Param("medicineId") Long medicineId);

    @Query("select pm from PatientMedicine pm join pm.treatment t where t.patient.id = :patientId")
    List<PatientMedicine> findAllByPatientId(@Param("patientId") Long patientId);
}
