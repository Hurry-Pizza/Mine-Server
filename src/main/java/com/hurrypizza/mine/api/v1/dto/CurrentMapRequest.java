package com.hurrypizza.mine.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CurrentMapRequest {

    private List<List<String>> currentMap;

}
