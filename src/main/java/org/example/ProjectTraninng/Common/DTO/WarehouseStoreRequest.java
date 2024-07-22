package org.example.ProjectTraninng.Common.DTO;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseStoreRequest {
    private Long medicineId;
    private Integer quantity;
}
