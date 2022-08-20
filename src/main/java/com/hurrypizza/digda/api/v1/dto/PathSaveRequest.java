package com.hurrypizza.digda.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PathSaveRequest {

    private List<List<String>> path;

}
