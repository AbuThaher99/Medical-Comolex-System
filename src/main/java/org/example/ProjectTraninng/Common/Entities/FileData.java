package org.example.ProjectTraninng.Common.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "fileData")
public class FileData extends BaseEntity  {


    private String name;
    private String type;
    private String filePath;

    @Transient
    private byte[] data;

}
