package com.hurrypizza.mine.domain.projection;

import com.hurrypizza.mine.util.PolygonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PathUser {

    private Long pathId;
    private List<List<String>> path;
    private Long userId;
    private String color;

    public static PathUser from(PathUserProjection projection) {
        var path = PolygonUtil.toPolygonList(projection.getArea());
        return new PathUser(projection.getPathId(),
                path,
                projection.getUserId(),
                projection.getColor());
    }

}
