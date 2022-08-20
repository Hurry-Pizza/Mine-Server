package com.hurrypizza.mine.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
public class PathSaveRequest {

    private List<List<String>> path;
    @NotNull(message = "필수 입력 항목입니다.")
    private Double area;

}
