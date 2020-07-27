package com.kosenkov;

import java.util.*;

import static com.kosenkov.MyRead.readTable;

public class Main {

    private final static String FILE_A = "tableA.csv";
    private final static String FILE_B = "tableB.csv";

    public static void main(String[] args) {

        // 1. ArrayList
        ArrayList<Row> tableA = readTable(FILE_A);
        ArrayList<Row> tableB = readTable(FILE_B);

        List<Row> ijList = MyArrList.innerJoinList(tableA, tableB);
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
        List<Row> ijSortList = MyLinkedList.innerJoinSortList(tableALinkedList, tableBLinkedList);

        MyPrint.printTable(ijSortList, "Внутренние соединение таблиц (LinkedList)", true);

        // 3. HashMap
        Map<Integer, List<String>> mapA = MyMap.createMapByRowList(tableA);
        MyPrint.printTable(mapA, "Таблица A (HashMap)");
        Map<Integer, List<String>> mapB = MyMap.createMapByRowList(tableB);
        MyPrint.printTable(mapB, "Таблица B (HashMap)");
        Map<Integer, List<String>> ijMap = MyMap.innerJoinMap(mapA, mapB);

        MyPrint.printTable(ijMap, "Внутренние соединение таблиц (HashMap)", true);
    }
}
