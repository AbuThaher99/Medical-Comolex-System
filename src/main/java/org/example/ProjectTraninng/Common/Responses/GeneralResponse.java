package org.example.ProjectTraninng.Common.Responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class GeneralResponse<T> {
    private  List<T> list;
    private int count;

}
