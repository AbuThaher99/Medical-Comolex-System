package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientMedicineRepository extends JpaRepository<PatientMedicine, Long> {

    @Query("select pm from PatientMedicine pm where pm.treatment.id = :treatmentId")
    Page<PatientMedicine> findAllByTreatmentId(@Param("treatmentId") Long treatmentId, Pageable pageable);

    @Query("select pm from PatientMedicine pm where pm.medicine.id = :medicineId")
    Page<PatientMedicine> findAllByMedicineId(@Param("medicineId") Long medicineId, Pageable pageable);

    @Query("select pm from PatientMedicine pm join pm.treatment t where t.patient.id = :patientId")
    Page<PatientMedicine> findAllByPatientId(@Param("patientId") Long patientId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from PatientMedicine pm where pm.id = :id")
    void deleteById(@Param("id") Long id);

    @Query("SELECT pm FROM PatientMedicine pm " +
            "WHERE (:search IS NULL or :search = '' or " +
            "cast(pm.price AS String) like lower(concat('%', :search, '%')) " +
            "or cast(pm.quantity AS string) like lower(concat('%', :search, '%'))) " +
            "AND (:treatmentIds IS NULL OR pm.treatment.id IN :treatmentIds) " +
            "AND (:medicineIds IS NULL OR pm.medicine.id IN :medicineIds) " +
            "AND (:patientIds IS NULL OR pm.treatment.patient.id IN :patientIds)")
    Page<PatientMedicine> findAll(Pageable pageable,
                                  @Param("search") String search,
                                  @Param("treatmentIds") List<Long> treatmentIds,
                                  @Param("medicineIds") List<Long> medicineIds,
                                  @Param("patientIds") List<Long> patientIds);
}
