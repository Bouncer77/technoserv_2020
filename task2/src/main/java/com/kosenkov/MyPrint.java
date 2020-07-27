package com.kosenkov;

import java.util.List;
import java.util.Map;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 25.07.2020
 */

public class MyPrint {

    private static final String DEFAULT_TABLE_NAME = "Таблица";
    private static final boolean DEFAULT_INNER_JOIN_TABLE = false;

    public static void printTable(Map<Integer, List<String>> map) {
        printTable(map, DEFAULT_TABLE_NAME, DEFAULT_INNER_JOIN_TABLE);
    }

    public static void printTable(Map<Integer, List<String>> map, String tableName) {
        printTable(map, tableName, DEFAULT_INNER_JOIN_TABLE);
    }

    public static void printTable(List<Row> rowList) {
        printTable(rowList, DEFAULT_TABLE_NAME, DEFAULT_INNER_JOIN_TABLE);
    }

    public static void printTable(List<Row> rowList, String tableName) {
        printTable(rowList, tableName, DEFAULT_INNER_JOIN_TABLE);
    }

    public static void printTable(List<Row> rowList, String tableName, boolean innerJoinTable) {

        printHeadTable(tableName, innerJoinTable);
        for (Row row : rowList) {
            System.out.println(row.getIndex() + "\t" + row.getValue());
        }
        System.out.println();
    }

    public static void printTable(Map<Integer, List<String>> map, String tableName, boolean innerJoinTable) {

        printHeadTable(tableName, innerJoinTable);
        for (Map.Entry<Integer, List<String>> el : map.entrySet()) {
            for (String str : el.getValue()) {
                System.out.println(el.getKey() + "\t" + str);
            }
        }
        System.out.println();
    }

    public static void printHeadTable(String tableName, boolean innerJoinTable) {
        System.out.println(tableName);
        if (innerJoinTable) {
            System.out.println("ID\tA.VALUE\tB.VALUE");
        } else {
            System.out.println("ID\tVALUE");
        }
    }
}
