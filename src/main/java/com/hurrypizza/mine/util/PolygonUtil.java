package com.hurrypizza.mine.util;

import java.util.Arrays;
import java.util.List;

public class PolygonUtil {

    public static final String POLYGON = "POLYGON";

    private PolygonUtil() {
    }

    public static List<List<String>> toPolygonList(String polygonString) {
        var routeData = polygonString.substring(9, polygonString.length() - 2);
        return Arrays.stream(routeData.split(","))
                       .map(point -> Arrays.stream(point.split(" ")).toList())
                       .toList();
    }

    public static String toPolygonString(List<List<String>> polygonList) {
        var points = polygonList.stream()
                             .map(point -> String.join(" ", point))
                             .toList();
        return "%s((%s))".formatted(POLYGON, String.join(",", points));
    }

}
