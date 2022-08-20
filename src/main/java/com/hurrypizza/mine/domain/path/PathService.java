package com.hurrypizza.mine.domain.path;

import com.hurrypizza.mine.domain.projection.PathUser;

import java.util.List;

public interface PathService {

    List<PathUser> getPathsWithinCurrentMap(final String currentMap);

    void savePath(final Long userId, final String path, final Double area);

}
