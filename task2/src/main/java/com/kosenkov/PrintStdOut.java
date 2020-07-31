package com.kosenkov;

import java.util.List;
import java.util.Map;

import static com.kosenkov.Colour.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 25.07.2020
 */

public class PrintStdOut {

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
            System.out.print(row.getIndex() + "\t");
            String str = row.getValue();
            colorPrintElements(str, innerJoinTable);
        }
        System.out.println();
    }

    public static void printTable(Map<Integer, List<String>> map, String tableName, boolean innerJoinTable) {

        printHeadTable(tableName, innerJoinTable);
        for (Map.Entry<Integer, List<String>> el : map.entrySet()) {
            for (String str : el.getValue()) {
                System.out.print(el.getKey() + "\t");
                colorPrintElements(str, innerJoinTable);
            }
        }
        System.out.println();
    }

    public static void printHeadTable(String tableName, boolean innerJoinTable) {

        if (innerJoinTable) {
            System.out.println(Colour.cyan(tableName));
            System.out.println(Colour.red("ID") + "\t" + Colour.green("A") + "." + Colour.yellow("VALUE") +
                    "\t" + Colour.blue("B") + "."+ Colour.yellow("VALUE"));
        } else {
            System.out.println(Colour.purple(tableName));
            System.out.println(Colour.red("ID") + "\t" + Colour.yellow("VALUE"));
        }
    }

    private static void colorPrintElements(String str, boolean innerJoinTable) {
        if (innerJoinTable) {
            String[] strings = str.split(" ");
            System.out.println(green(strings[0]) + "\t" + blue(strings[1]));
        } else {
            System.out.println(green(str));
        }
    }
}
