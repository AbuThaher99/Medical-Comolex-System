package org.example.ProjectTraninng.Common.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMedicineRequest {
    private Integer quantity;
    private Double price;
    private Long treatmentId;
    private Long medicineId;
}
