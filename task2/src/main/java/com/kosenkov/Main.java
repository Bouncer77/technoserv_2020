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
        ArrayList<Row> tableA = readTable(FILE_A);
        ArrayList<Row> tableB = readTable(FILE_B);

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
        LinkedList<Row> tableALinkedList = new LinkedList<>(tableA);
        Collections.sort(tableALinkedList);
        System.out.println("Отсортированная таблица A");
        MyPrint.printListTable(tableALinkedList, false);
        LinkedList<Row> tableBLinkedList = new LinkedList<>(tableB);
        Collections.sort(tableBLinkedList);
        System.out.println("Отсортированная таблица B");
        MyPrint.printListTable(tableBLinkedList, false);
        List<Row> ijSortList = Main.innerJoinSortList(tableALinkedList, tableBLinkedList);
        /*List<Row> ijSortList2 = Main.innerJoinSortList(tableBLinkedList, tableALinkedList);*/

        // 2 out
        System.out.println("innerJoinSortList");
        MyPrint.printListTable(ijSortList, true);
        System.out.println();
        /*System.out.println("innerJoinSortList2");
        MyPrint.printListTable(ijSortList2, true);
        System.out.println();*/

        /*List<Row> test1 = List.of(
                new Row(0, "N"),
                new Row(4, "N"),
                new Row(7, "N"),
                new Row(8, "M"),
                new Row(9, "K")
        );
        List<Row> ijSortList3 = Main.innerJoinSortList(tableALinkedList, test1);
        System.out.println("innerJoinSortList3");
        MyPrint.printListTable(ijSortList3, true);
        System.out.println();*/


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

    public static ArrayList<Row> readTable(String fileName) {
        ArrayList<Row> rowList = new ArrayList<>();
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
                    resList.add(new Row(row1.getIndex(), row1.getValue() + " " + row2.getValue()));
                }
            }
        }

        /*rowList1.forEach(row1 -> rowList2.forEach(row2 -> {
            if (row1.getIndex() == row2.getIndex()) {
                resList.add(new Row(row1.getIndex(), row1.getString() + " " + row2.getString()));
            }
        }));*/
        return resList;
    }

    /*private static List<Row> findDubIndexList(List<Row> rowList, ListIterator<Row> listIterator) {
        List<Row> resList = new LinkedList<>();
        if (rowList.isEmpty() || !listIterator.hasNext()) return resList;

        Row row1 = null;

        // next = первому элементу    / Если == rowList.iterator(); те указатель на двухсвязный список
        if (!listIterator.hasPrevious()) {
            row1 = listIterator.next();
            // Последний элемент
        } else if (rowList.listIterator(rowList.size() - 1).equals(listIterator)) {
            listIterator.previous();
            resList.add(listIterator.next());
            return resList;
        } else {
            listIterator.previous();
            row1 = listIterator.next();
        }

        *//*if (listIterator.hasPrevious()) {
            row1 = listIterator.previous();
        } else {
            if (listIterator.hasNext()) {
                row1 = listIterator.next();
            }
        }*//*
        //Row row1 = listIterator.next();
        resList.add(row1);
        while (listIterator.hasNext()) {
            Row row2 = listIterator.next();
            if (row2.getIndex() == row1.getIndex()) {
                resList.add(row2);
            } else {
                return resList;
            }
        }
        return resList;
    }*/


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
            List<Row> tmp1 = findDubIndexList(itrList1);
            //System.out.println("TMP1:");
            //MyPrint.printListTable(tmp1, false);
            int curId = tmp1.get(0).getIndex();
            if (findId(curId, itrList2)) {

                //System.out.println(itrList2.next());
                //itrList2.previous();

                List<Row> tmp2 = findDubIndexList(itrList2);
                //System.out.println("TMP2:");
                //MyPrint.printListTable(tmp2, false);

                // Объединение списков
                for (Row r1 : tmp1) {
                    for (Row r2 : tmp2) {
                        resList.add(new Row(r1.getIndex(), r1.getValue() + " " + r2.getValue()));
                    }
                }
            }
        }

       /* Row curRowList1 = itrList1.next();
        Row curRowList2 = itrList2.next();

        List<Row> dublicateList1 = new LinkedList<>();
        List<Row> dublicateList2 = new LinkedList<>();

        while (itrList1.hasNext()) {


        }*/







        /*dublicateList1.add(curRowList1);
        while (itrList1.hasNext()) {
            Row curRowList1next = itrList1.next();

            if (curRowList1next.getIndex() == curRowList1.getIndex()) {
                dublicateList1.add(curRowList1next);
            } else {

                if (curRowList2.getIndex() > curRowList1.getIndex())
                    continue;

                while (itrList2.hasNext()) {

                    Row curRowList2next = itrList2.next();

                    if (curRowList2next.getIndex() == curRowList2.getIndex()) {
                        dublicateList2.add(curRowList1next);
                    } else {
                        // Получить все комбинации и записать их в результирующий список
                        System.out.println("****************************");
                        System.out.println("List1");
                        MyPrint.printListTable(dublicateList1, false);
                        System.out.println("List2");
                        MyPrint.printListTable(dublicateList2, false);
                        System.out.println("****************************");
                        resList.addAll(innerJoinList(dublicateList1, dublicateList2));
                        dublicateList1.clear();
                        dublicateList2.clear();
                    }
                    curRowList2 = curRowList2next;
                }
            }
            curRowList1 = curRowList1next;
        }
        System.out.println("Res List");
        MyPrint.printListTable(resList, false);*/
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
