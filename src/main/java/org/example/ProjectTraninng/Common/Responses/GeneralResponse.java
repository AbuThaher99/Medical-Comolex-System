package org.example.ProjectTraninng.Common.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
    @JsonProperty
    private String message;

}
