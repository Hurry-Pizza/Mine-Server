package com.hurrypizza.mine.api.v1;

import com.hurrypizza.mine.api.ApiResponse;
import com.hurrypizza.mine.api.v1.dto.CurrentMapRequest;
import com.hurrypizza.mine.api.v1.dto.PathSaveRequest;
import com.hurrypizza.mine.config.security.util.SecurityUtils;
import com.hurrypizza.mine.domain.path.Path;
import com.hurrypizza.mine.domain.path.PathRouteRepository;
import com.hurrypizza.mine.domain.path.PathService;
import com.hurrypizza.mine.domain.projection.PathUser;
import com.hurrypizza.mine.util.PolygonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final PathRouteRepository pathRouteRepository;
    private final PathService pathService;

    @GetMapping
    public ApiResponse<List<Long>> paths() {
        return ApiResponse.of(pathRouteRepository.findAll().stream().map(Path::getId).toList());
    }

    @GetMapping("/route")
    public ApiResponse<?> routes() {
        var allRoute = pathRouteRepository.findAllRoute().stream()
                              .map(PolygonUtil::toPolygonList)
                              .toList();
        return ApiResponse.of(allRoute);
    }

    @GetMapping("/{pathId}/route")
    public ApiResponse<String> oneRoute(@PathVariable Long pathId) {
        var route = pathRouteRepository.findRouteById(pathId).orElse(null);
        return ApiResponse.of(route);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> saveOnePath(@RequestBody PathSaveRequest pathSaveRequest) {
        var userId = SecurityUtils.getCurrentUserInfo().getId();
        var path = PolygonUtil.toPolygonString(pathSaveRequest.getPath());
        pathService.savePath(userId, path);
        return ApiResponse.emptyResponse();
    }

    @GetMapping("/within")
    public ApiResponse<List<PathUser>> allPathsWithinCurrentMap(@RequestBody CurrentMapRequest currentMapRequest) {


        var currentMap = PolygonUtil.toPolygonString(currentMapRequest.getCurrentMap());
        var pathUsers = pathService.getPathsWithinCurrentMap(currentMap);
        return ApiResponse.of(pathUsers);
    }

}
