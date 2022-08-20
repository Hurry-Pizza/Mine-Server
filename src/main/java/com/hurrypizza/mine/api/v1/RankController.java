package com.hurrypizza.mine.api.v1;

import com.hurrypizza.mine.api.ApiResponse;
import com.hurrypizza.mine.api.v1.dto.RankViewResponse;
import com.hurrypizza.mine.domain.path.RankService;
import com.hurrypizza.mine.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;

@RestController
@RequestMapping("/v1/ranks")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    @GetMapping
    public ApiResponse<RankViewResponse> ranks() {
        var criteriaDateTime = DateTimeUtil.getDayOfWeek(DayOfWeek.MONDAY);
        var ranks = rankService.getRankingInfos(criteriaDateTime);
        var startDay = criteriaDateTime.toLocalDate();
        var endDay = startDay.plusDays(6);
        return ApiResponse.of(RankViewResponse.create(ranks, startDay, endDay));
    }

}
