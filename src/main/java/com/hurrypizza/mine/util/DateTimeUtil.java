package com.hurrypizza.mine.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateTimeUtil {

    private static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");

    private DateTimeUtil() {
    }

    public static LocalDateTime getDayOfWeek(DayOfWeek dayOfWeek) {
        return LocalDateTime.now(SEOUL_ZONE_ID)
                       .with(dayOfWeek)
                       .with(LocalTime.MIDNIGHT);
    }

    public static LocalDateTime getDayOfWeek(LocalDateTime localDateTime, DayOfWeek dayOfWeek) {
        return localDateTime.with(dayOfWeek)
                       .with(LocalTime.MIDNIGHT);
    }

}
