package com.hurrypizza.mine.domain.projection;

import lombok.Getter;

@Getter
public class UserRankingInfo {

    private final long userId;
    private final String userNickname;
    private final double area;

    private UserRankingInfo(long userId, String userNickname, double area) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.area = area;
    }

    public static UserRankingInfo create(final long userId,
                                         final String userNickname,
                                         final double area) {
        return new UserRankingInfo(userId, userNickname, area);
    }

}
