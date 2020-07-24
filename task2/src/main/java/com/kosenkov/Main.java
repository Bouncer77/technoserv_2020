package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class Main {

    private final static String FILE_A = "tableA.csv";
    private final static String FILE_B = "tableB.csv";

    public static void main( String[] args ) {

        // 1. ArrayList
        List<Row> tableA = readTable(FILE_A);
        List<Row> tableB = readTable(FILE_B);

        //System.out.println("tableA: " + tableA);
        //System.out.println("tableB: " + tableB);
        //System.out.println();
        List<Row> ijList = Main.innerJoinList(tableA, tableB);
        //System.out.println("innerJoinList: " + ijList);
        System.out.println("Table A");
        MyPrint.printListTable(tableA, false);
        System.out.println();
        System.out.println("Table B");
        MyPrint.printListTable(tableB, false);
        System.out.println();

        // 1 out
        System.out.println("innerJoinList");
        MyPrint.printListTable(ijList, true);
        System.out.println();

        // 2. Отсортированный LinkedList
        List<Row> tableALinkedList = new LinkedList<>(tableA);
        Collections.sort(tableALinkedList);
        List<Row> tableBLinkedList = new LinkedList<>(tableB);
        Collections.sort(tableBLinkedList);
        List<Row> ijSortList = Main.innerJoinSortList(tableALinkedList, tableBLinkedList);

        // 2 out
        System.out.println("innerJoinSortList");
        MyPrint.printListTable(ijSortList, true);
        System.out.println();

        //System.out.println("tableALinkedList: " + tableALinkedList);
        //System.out.println("tableBLinkedList: " + tableBLinkedList);

        // 3. HashMap
        Map<Integer, List<String>> mapA = Main.createMapByRowList(tableA);
        Map<Integer, List<String>> mapB = Main.createMapByRowList(tableB);

        //System.out.println("mapA: " + mapA);
        //System.out.println("mapB: " + mapB);
        Map<Integer, List<String>> ijMap = Main.innerJoinMap(tableA, tableB);

        // 3 out
        System.out.println("innerJoinMap");
        MyPrint.printMapTable(ijMap, true);
    }

    public static List<Row> readTable(String fileName) {
        List<Row> rowList = new ArrayList<>();
        try(final Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] inputFields = scanner.nextLine().split(",");
                for (int i = 0; i < inputFields.length; i++)
                    inputFields[i] = inputFields[i].trim();

                if (Row.validationRow(inputFields)) {
                    rowList.add(new Row(Integer.parseInt(inputFields[Row.ID]), inputFields[Row.VALUE]));
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден!");
        }

        return rowList;
    }

    // 1. ArrayList
    public static List<Row> innerJoinList(List<Row> rowList1, List<Row> rowList2) {
        List<Row> resList = new ArrayList<>();
        for (Row row1 : rowList1) {
            for (Row row2 : rowList2) {
                if (row1.getIndex() == row2.getIndex()) {
                    resList.add(new Row(row1.getIndex(), row1.getString() + " " + row2.getString()));
                }
            }
        }
        return resList;
    }

    // 2. Отсортированный LinkedList
    public static List<Row> innerJoinSortList(List<Row> rowList1, List<Row> rowList2) {
        List<Row> resList = new LinkedList<>();
        rowList1.forEach(row1 -> rowList2.forEach(row2 -> {
            if (row1.getIndex() == row2.getIndex()) {
                resList.add(new Row(row1.getIndex(), row1.getString() + " " + row2.getString()));
            }
        }));
        return resList;
    }

    // 3. HashMap
    public static Map<Integer, List<String>> innerJoinMap(List<Row> rowList1, List<Row> rowList2) {
        Map<Integer, List<String>> resMap = new HashMap<>();

        for (Row row1 : rowList1) {
            for (Row row2 : rowList2) {
                if (row1.getIndex() == row2.getIndex()) {
                    if (!resMap.containsKey(row1.getIndex())) {
                        List<String> strList = new LinkedList<>();
                        strList.add(row1.getString() + " " + row2.getString());
                        resMap.put(row1.getIndex(), strList);
                    } else {
                        resMap.get(row1.getIndex()).add(row1.getString() + " " + row2.getString());
                    }
                }
            }
        }

        return resMap;
    }

    private static Map<Integer, List<String>> createMapByRowList(List<Row> rowList) {
        Map<Integer, List<String>> resMap = new HashMap<>();
        for (Row row : rowList) {
            if (!resMap.containsKey(row.getIndex())) {
                List<String> stringList = new LinkedList<>();
                stringList.add(row.getString());
                resMap.put(row.getIndex(), stringList);
            } else {
                resMap.get(row.getIndex()).add(row.getString());
            }
        }
        return resMap;
    }
}
