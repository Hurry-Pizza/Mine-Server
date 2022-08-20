package com.hurrypizza.mine.domain.path;

import com.hurrypizza.mine.domain.projection.UserRankingInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface RankService {

    List<UserRankingInfo> getRankingInfos(final LocalDateTime criteriaDateTime);

}
