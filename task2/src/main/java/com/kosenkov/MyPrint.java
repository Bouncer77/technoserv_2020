package com.kosenkov;

import java.util.List;
import java.util.Map;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 25.07.2020
 */

public class MyPrint {

    public static void printListTable(List<Row> rowList, boolean innerJoinTable) {

        printHeadTable(innerJoinTable);
        for (Row row : rowList) {
            System.out.println(row.getIndex() + "\t" + row.getValue());
        }
    }

    public static void printMapTable(Map<Integer, List<String>> map, boolean innerJoinTable) {
        printHeadTable(innerJoinTable);
        for (Map.Entry<Integer, List<String>> el : map.entrySet()) {
            for (String str : el.getValue()) {
                System.out.println(el.getKey() + "\t" + str);
            }
        }
    }

    public static void printHeadTable(boolean innerJoinTable) {
        if (innerJoinTable) {
            System.out.println("ID\tA.VALUE\tB.VALUE");
        } else {
            System.out.println("ID\tVALUE");
        }
    }
}
