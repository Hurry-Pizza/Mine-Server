package com.hurrypizza.mine.api.v1;

import com.hurrypizza.mine.api.ApiResponse;
import com.hurrypizza.mine.domain.path.RankService;
import com.hurrypizza.mine.domain.projection.UserRankingInfo;
import com.hurrypizza.mine.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/v1/ranks")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    @GetMapping
    public ApiResponse<List<UserRankingInfo>> ranks() {
        var criteriaDateTime = DateTimeUtil.getDayOfWeek(DayOfWeek.MONDAY);
        var ranks = rankService.getRankingInfos(criteriaDateTime);
        return ApiResponse.of(ranks);
    }

}
