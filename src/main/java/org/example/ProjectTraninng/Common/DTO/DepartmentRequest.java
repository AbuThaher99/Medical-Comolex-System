package org.example.ProjectTraninng.Common.DTO;

import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class DepartmentRequest {
    @NotNull(message = "Department Name is required")
    private String name;
    private Long headDepartmentId;
    private Long secretaryId;
}