package org.example.ProjectTraninng.Common.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentResponse {
    @JsonProperty
    private String message;
}
