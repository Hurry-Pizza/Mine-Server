package com.hurrypizza.mine.util;

import java.util.Arrays;
import java.util.List;

public class PolygonUtil {

    public static final String POLYGON = "POLYGON";

    private PolygonUtil() {
    }

    public static List<List<String>> toPolygonList(String polygonString) {
        var areaData = polygonString.substring(9, polygonString.length() - 2);
        return Arrays.stream(areaData.split(","))
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
