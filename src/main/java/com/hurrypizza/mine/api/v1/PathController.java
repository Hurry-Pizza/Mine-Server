package com.hurrypizza.mine.api.v1;

import com.hurrypizza.mine.api.ApiResponse;
import com.hurrypizza.mine.api.v1.dto.CurrentMapRequest;
import com.hurrypizza.mine.api.v1.dto.PathSaveRequest;
import com.hurrypizza.mine.config.security.util.SecurityUtils;
import com.hurrypizza.mine.domain.path.PathService;
import com.hurrypizza.mine.domain.projection.PathUser;
import com.hurrypizza.mine.util.PolygonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/paths")
@RequiredArgsConstructor
public class PathController {

    private final PathService pathService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> saveOnePath(@RequestBody @Validated PathSaveRequest pathSaveRequest) {
        var userId = SecurityUtils.getCurrentUserInfo().getId();
        var path = PolygonUtil.toPolygonString(pathSaveRequest.getPath());
        pathService.savePath(userId, path, pathSaveRequest.getArea());
        return ApiResponse.emptyResponse();
    }

    @PutMapping("/within")
    public ApiResponse<List<PathUser>> allPathsWithinCurrentMap(@RequestBody CurrentMapRequest currentMapRequest) {
        var currentMap = PolygonUtil.toPolygonString(currentMapRequest.getCurrentMap());
        var pathUsers = pathService.getPathsWithinCurrentMap(currentMap);
        return ApiResponse.of(pathUsers);
    }

}
