package org.example.ProjectTraninng.Common.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {
    private  String name;
    private Double buyPrice;
    private Double purchasePrice;
    private Date expirationDate;
}
