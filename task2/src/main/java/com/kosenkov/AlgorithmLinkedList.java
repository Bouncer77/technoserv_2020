package com.kosenkov;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class AlgorithmLinkedList {
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
}
