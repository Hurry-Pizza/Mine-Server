package com.hurrypizza.digda.api.v1;

import com.hurrypizza.digda.api.ApiResponse;
import com.hurrypizza.digda.domain.Path;
import com.hurrypizza.digda.domain.PathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/paths")
@RequiredArgsConstructor
public class PathController {

    private final PathRepository pathRepository;

    @GetMapping
    public ApiResponse<List<Long>> paths() {
        return ApiResponse.of(pathRepository.findAll().stream().map(Path::getId).toList());
    }

}
