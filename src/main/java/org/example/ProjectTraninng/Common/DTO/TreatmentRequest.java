package org.example.ProjectTraninng.Common.DTO;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentRequest {
    private Long patientId;
    private Long doctorId;
    private String description;
    private String note;

}
