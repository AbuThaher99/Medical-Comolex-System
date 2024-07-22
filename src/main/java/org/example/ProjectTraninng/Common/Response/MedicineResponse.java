package org.example.ProjectTraninng.Common.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponse {
    @JsonProperty
    private String message;
}
