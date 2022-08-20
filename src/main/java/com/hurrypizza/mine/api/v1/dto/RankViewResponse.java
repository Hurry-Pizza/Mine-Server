package com.hurrypizza.mine.api.v1.dto;

import com.hurrypizza.mine.domain.projection.UserRankingInfo;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static com.hurrypizza.mine.util.DateTimeUtil.DATE_FORMATTER;

@Getter
public class RankViewResponse {

    private List<UserRankingInfo> ranks;
    private String startDay;
    private String endDay;

    private RankViewResponse(List<UserRankingInfo> ranks, String startDay, String endDay) {
        this.ranks = ranks;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public static RankViewResponse create(List<UserRankingInfo> ranks, LocalDate startDay, LocalDate endDay) {
        return new RankViewResponse(ranks, startDay.format(DATE_FORMATTER), endDay.format(DATE_FORMATTER));
    }

}
