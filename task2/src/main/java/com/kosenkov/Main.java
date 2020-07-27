package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private final static String FILE_A = "tableA.csv";
    private final static String FILE_B = "tableB.csv";

    public static void main(String[] args) {

        // 1. ArrayList
        ArrayList<Row> tableA = readTable(FILE_A);
        ArrayList<Row> tableB = readTable(FILE_B);

        List<Row> ijList = Main.innerJoinList(tableA, tableB);
        MyPrint.printTable(tableA, "Table A");
        MyPrint.printTable(tableB, "Table B");

        MyPrint.printTable(ijList, "Внутренние соединение таблиц (ArrayList)", true);

        // 2. Отсортированный LinkedList
        LinkedList<Row> tableALinkedList = new LinkedList<>(tableA);
        Collections.sort(tableALinkedList);
        MyPrint.printTable(tableALinkedList, "Отсортированная по ID таблица A");
        LinkedList<Row> tableBLinkedList = new LinkedList<>(tableB);
        Collections.sort(tableBLinkedList);
        MyPrint.printTable(tableBLinkedList, "Отсортированная по ID таблица B");
        List<Row> ijSortList = Main.innerJoinSortList(tableALinkedList, tableBLinkedList);

        MyPrint.printTable(ijSortList, "Внутренние соединение таблиц (LinkedList)", true);

        // 3. HashMap
        Map<Integer, List<String>> mapA = Main.createMapByRowList(tableA);
        MyPrint.printTable(mapA, "Таблица A (HashMap)");
        Map<Integer, List<String>> mapB = Main.createMapByRowList(tableB);
        MyPrint.printTable(mapB, "Таблица B (HashMap)");
        Map<Integer, List<String>> ijMap = Main.innerJoinMap(mapA, mapB);

        MyPrint.printTable(ijMap, "Внутренние соединение таблиц (HashMap)", true);
    }

    public static ArrayList<Row> readTable(String fileName) {
        ArrayList<Row> rowList = new ArrayList<>();
        try (final Scanner scanner = new Scanner(new File(fileName))) {
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

    private static List<Row> findDubIndexList(ListIterator<Row> listIterator) {
        List<Row> resList = new LinkedList<>();
        Row startRow = listIterator.next();
        resList.add(startRow);

        while (listIterator.hasNext()) {
            Row curRow = listIterator.next();
            if (startRow.getIndex() == curRow.getIndex()) {
                resList.add(curRow);
            } else {
                listIterator.previous();
                return resList;
            }
        }

        return resList;
    }

    private static boolean findId(int id, ListIterator<Row> iterator) {
        while (iterator.hasNext()) {
            Row curRow = iterator.next();
            if (curRow.getIndex() > id) {
                iterator.previous();
                return false;
            } else if (curRow.getIndex() == id) {
                iterator.previous();
                return true;
            }
        }
        return false;
    }

    // 2. Отсортированный LinkedList
    public static List<Row> innerJoinSortList(LinkedList<Row> rowList1, LinkedList<Row> rowList2) {
        List<Row> resList = new LinkedList<>();

        if (rowList1.isEmpty() || rowList2.isEmpty()) return resList;

        ListIterator<Row> itrList1 = rowList1.listIterator();
        ListIterator<Row> itrList2 = rowList2.listIterator();

        while (itrList1.hasNext()) {
            List<Row> dubList1 = findDubIndexList(itrList1);
            int curId = dubList1.get(0).getIndex();
            if (findId(curId, itrList2)) {
                List<Row> dubList2 = findDubIndexList(itrList2);
                for (Row r1 : dubList1) {
                    for (Row r2 : dubList2) {
                        resList.add(new Row(r1.getIndex(), r1.getValue() + " " + r2.getValue()));
                    }
                }
            }
        }
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

    private static Map<Integer, List<String>> createMapByRowList(List<Row> rowList) {
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
