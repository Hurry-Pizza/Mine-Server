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

    private long pathId;
    private List<List<String>> path;
    private long userId;
    private String userNickname;
    private String color;
    private boolean isMine;

    public static PathUser from(PathUserProjection projection, long currentUserId) {
        var path = PolygonUtil.toPolygonList(projection.getRoute());
        return new PathUser(projection.getPathId(),
                path,
                projection.getUserId(),
                projection.getUserNickname(),
                projection.getColor(),
                currentUserId == projection.getUserId());
    }

}
