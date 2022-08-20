package com.hurrypizza.mine.domain.path;

import com.hurrypizza.mine.config.security.util.SecurityUtils;
import com.hurrypizza.mine.domain.projection.PathUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PathServiceImpl implements PathService {

    private final PathRouteRepository pathRouteRepository;

    @Override
    public List<PathUser> getPathsWithinCurrentMap(final String currentMap) {
        return pathRouteRepository.findAllPathWithin(currentMap).stream()
                       .map(projection -> PathUser.from(projection, getCurrentUserId()))
                       .toList();
    }

    @Transactional
    @Override
    public void savePath(Long userId, String path, Double area) {
        pathRouteRepository.save(userId, path, area);
    }

    private long getCurrentUserId() {
        if (SecurityUtils.isAuthenticated()) {
            return SecurityUtils.getCurrentUserInfo().getId();
        }
        return -1;
    }

}