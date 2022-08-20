package com.hurrypizza.digda.domain.path;

import com.hurrypizza.digda.domain.projection.PathUser;

import java.util.List;

public interface PathService {

    List<PathUser> getPathsWithinCurrentMap(final String currentMap);

}
