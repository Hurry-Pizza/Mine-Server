package com.hurrypizza.digda.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PolygonUtilTest {

    @Test
    void polygonStringTest() {
        var firstPoint = List.of("123", "532");
        var secondPoint = List.of("5324", "123");
        var pointList = List.of(
                firstPoint,
                secondPoint,
                List.of("654", "564654"),
                List.of("2332", "5746")
        );

        var polygonString = PolygonUtil.toPolygonString(pointList);

        assertThat(polygonString)
                .contains(String.join(" ", firstPoint),
                        String.join(" ", secondPoint));
    }

}