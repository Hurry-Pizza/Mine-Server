package com.hurrypizza.digda.api.v1;

import com.hurrypizza.digda.api.ApiResponse;
import com.hurrypizza.digda.api.v1.dto.PathSaveRequest;
import com.hurrypizza.digda.domain.Path;
import com.hurrypizza.digda.domain.PathRepository;
import com.hurrypizza.digda.util.PolygonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/area")
    public ApiResponse<?> areas() {
        var allArea = pathRepository.findAllArea().stream()
                              .map(PolygonUtil::toPolygonList)
                              .toList();
        return ApiResponse.of(allArea);
    }

    @GetMapping("/{pathId}/area")
    public ApiResponse<String> oneArea(@PathVariable Long pathId) {
        var area = pathRepository.findAreaById(pathId).orElse(null);
        return ApiResponse.of(area);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void saveOnePath(@RequestBody PathSaveRequest pathSaveRequest) {
        var area = PolygonUtil.toPolygonString(pathSaveRequest.getPath());
        pathRepository.saveArea(1L, area);
    }

}
