package com.hurrypizza.mine.domain.path;

import com.hurrypizza.mine.domain.projection.UserRankingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankServiceImpl implements RankService {

    private final PathRepository pathRepository;

    @Override
    public List<UserRankingInfo> getRankingInfos(LocalDateTime criteriaDateTime) {
        var pathsByUserId = pathRepository.findAllByCreatedAtGreaterThanEqual(criteriaDateTime)
                                    .stream().collect(Collectors.groupingBy(path -> path.getUser()));
        var userByArea = pathsByUserId.entrySet().stream()
                                 .collect(Collectors.toMap(
                                         e -> e.getValue().stream()
                                                      .map(Path::getArea)
                                                      .reduce((double) 0, Double::sum), Map.Entry::getKey));
        return userByArea.keySet().stream()
                       .sorted(Comparator.reverseOrder())
                       .map(area -> {
                           var user = userByArea.get(area);
                           return UserRankingInfo.create(user.getId(), user.getNickname(), area);
                       })
                       .toList();
    }

}