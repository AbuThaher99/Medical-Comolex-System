package org.example.ProjectTraninng.Common.DTO;

import lombok.*;
import org.example.ProjectTraninng.Common.Entities.User;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;

    private User headDepartment;
    private User secretary;
}
