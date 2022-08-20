package com.hurrypizza.digda.domain.path;

import com.hurrypizza.digda.domain.projection.PathUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PathServiceImpl implements PathService {

    private final PathAreaRepository pathAreaRepository;

    @Override
    public List<PathUser> getPathsWithinCurrentMap(final String currentMap) {
        return pathAreaRepository.findAllPathWithin(currentMap).stream()
                       .map(PathUser::from)
                       .toList();
    }

    @Transactional
    @Override
    public void savePath(Long userId, String path) {
        pathAreaRepository.save(userId, path);
    }

}