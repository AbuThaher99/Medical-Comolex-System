package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.BloodType;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {
    @Query("select b from BloodType b where b.type = :type")
    BloodType findByType(@Param("type") BloodTypes type);
    @Query("select b from BloodType b where b.id = :id")
    Optional<BloodType> findById(@Param("id")Long id);

}
