package com.kosenkov;

import java.util.*;

import static com.kosenkov.ReadInputFile.readTable;

public class Main {

    private final static String FILE_A = "tableA.csv";
    private final static String FILE_B = "tableB.csv";

    public static void main(String[] args) {

        // 1. ArrayList
        ArrayList<Row> tableA = readTable(FILE_A);
        ArrayList<Row> tableB = readTable(FILE_B);

        List<Row> ijList = AlgorithmArrayList.innerJoinList(tableA, tableB);
        PrintStdOut.printTable(tableA, "Таблица A");
        PrintStdOut.printTable(tableB, "Таблица B");

        PrintStdOut.printTable(ijList, "Внутренние соединение таблиц (ArrayList)", true);

        // 2. Отсортированный LinkedList
        LinkedList<Row> tableALinkedList = new LinkedList<>(tableA);
        Collections.sort(tableALinkedList);
        PrintStdOut.printTable(tableALinkedList, "Отсортированная по ID таблица A");
        LinkedList<Row> tableBLinkedList = new LinkedList<>(tableB);
        Collections.sort(tableBLinkedList);
        PrintStdOut.printTable(tableBLinkedList, "Отсортированная по ID таблица B");
        List<Row> ijSortList = AlgorithmLinkedList.innerJoinSortList(tableALinkedList, tableBLinkedList);

        PrintStdOut.printTable(ijSortList, "Внутренние соединение таблиц (LinkedList)", true);

        // 3. HashMap
        Map<Integer, List<String>> mapA = AlgorithmMap.createMapByRowList(tableA);
        PrintStdOut.printTable(mapA, "Таблица A (HashMap)");
        Map<Integer, List<String>> mapB = AlgorithmMap.createMapByRowList(tableB);
        PrintStdOut.printTable(mapB, "Таблица B (HashMap)");
        Map<Integer, List<String>> ijMap = AlgorithmMap.innerJoinMap(mapA, mapB);

        PrintStdOut.printTable(ijMap, "Внутренние соединение таблиц (HashMap)", true);
    }
}
