package com.hurrypizza.mine.api.v1.dto;

import lombok.Getter;

@Getter
public class RankInfoResponse {

    private final double area;
    private final long userId;
    private final String nickname;

    public RankInfoResponse(double area, long userId, String nickname) {
        this.area = area;
        this.userId = userId;
        this.nickname = nickname;
    }

}
