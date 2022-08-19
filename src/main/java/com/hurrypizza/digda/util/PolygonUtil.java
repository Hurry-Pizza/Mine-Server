package com.hurrypizza.digda.util;

import java.util.Arrays;
import java.util.List;

public class PolygonUtil {
    private PolygonUtil() {
    }

    public static List<List<String>> toPointList(String area) {
        var areaData = area.substring(9, area.length() - 2);
        return Arrays.stream(areaData.split(","))
                       .map(point -> Arrays.stream(point.split(" ")).toList())
                       .toList();
    }

}
