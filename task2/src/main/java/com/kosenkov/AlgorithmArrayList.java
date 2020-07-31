package com.kosenkov;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmArrayList {
    // 1. ArrayList
    public static List<Row> innerJoinList(List<Row> rowList1, List<Row> rowList2) {

        // Итеративный алгоритм
        List<Row> resList = new ArrayList<>();
        for (Row row1 : rowList1) {
            for (Row row2 : rowList2) {
                if (row1.getIndex() == row2.getIndex()) {
                    resList.add(new Row(row1.getIndex(), row1.getValue() + " " + row2.getValue()));
                }
            }
        }

        // Через StreamAPI и лямбда выражения
        /*rowList1.forEach(row1 -> rowList2.forEach(row2 -> {
            if (row1.getIndex() == row2.getIndex()) {
                resList.add(new Row(row1.getIndex(), row1.getString() + " " + row2.getString()));
            }
        }));*/
        return resList;
    }
}
