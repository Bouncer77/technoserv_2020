package com.kosenkov;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyMap {

    // 3. HashMap
    public static Map<Integer, List<String>> innerJoinMap(List<Row> rowList1, List<Row> rowList2) {
        Map<Integer, List<String>> resMap = new HashMap<>();

        for (Row row1 : rowList1) {
            for (Row row2 : rowList2) {
                if (row1.getIndex() == row2.getIndex()) {
                    if (!resMap.containsKey(row1.getIndex())) {
                        List<String> strList = new LinkedList<>();
                        strList.add(row1.getValue() + " " + row2.getValue());
                        resMap.put(row1.getIndex(), strList);
                    } else {
                        resMap.get(row1.getIndex()).add(row1.getValue() + " " + row2.getValue());
                    }
                }
            }
        }

        return resMap;
    }

    public static Map<Integer, List<String>> innerJoinMap(Map<Integer, List<String>> mapA,
                                                          Map<Integer, List<String>> mapB) {
        Map<Integer, List<String>> resMap = new HashMap<>();

        for (Map.Entry<Integer, List<String>> entryA : mapA.entrySet()) {
            for (Map.Entry<Integer, List<String>> entryB : mapB.entrySet()) {
                if (entryA.getKey().equals(entryB.getKey())) {
                    if (!resMap.containsKey(entryA.getKey())) {
                        List<String> strList = new LinkedList<>();
                        for (String str1 : entryA.getValue()) {
                            for (String str2 : entryB.getValue()) {
                                strList.add(str1 + " " + str2);
                            }
                        }
                        resMap.put(entryA.getKey(), strList);
                    }
                }
            }
        }

        return resMap;
    }

    static Map<Integer, List<String>> createMapByRowList(List<Row> rowList) {
        Map<Integer, List<String>> resMap = new HashMap<>();
        for (Row row : rowList) {
            if (!resMap.containsKey(row.getIndex())) {
                List<String> stringList = new LinkedList<>();
                stringList.add(row.getValue());
                resMap.put(row.getIndex(), stringList);
            } else {
                resMap.get(row.getIndex()).add(row.getValue());
            }
        }
        return resMap;
    }
}
