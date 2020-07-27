package com.kosenkov;

import java.util.Arrays;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 24.07.2020
 */

public class Row implements Comparable<Row> {

    private final static int MAX_LENE_LENGTH = 100;
    public final static int ID = 0;
    public final static int VALUE = 1;

    private int index;
    private String value;

    public Row(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static boolean validationRow(String[] strings) {

        if (strings.length != 2) {
            System.out.println("Количество аргументов в строке больше двух.");
            System.out.println(Colour.yellow("Строка отброшена: ") + Arrays.toString(strings));
            return false;
        }

        if (!strings[ID].matches("\\d+")) {
            return false;
        }

        return strings[VALUE].length() <= MAX_LENE_LENGTH;
    }

    int getIndex() {
        return index;
    }

    String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        if (index != row.index) return false;
        return value.equals(row.value);
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Row{" +
                "index=" + index +
                ", string='" + value + '\'' +
                '}';
    }

    // for index
    @Override
    public int compareTo(Row o) {
        return Integer.compare(this.index, o.index);
    }

    // for string length
    /*@Override
    public int compareTo(Row o) {
        if (this.string.length() > o.string.length()) {
            return 1;
        } else if (this.string.length() < o.string.length()) {
            return -1;
        } else {
            return 0;
        }
    }*/

    // for string (сравнение строк в лексикографическом порядке java)
    /*@Override
    public int compareTo(Row o) {
        return this.string.compareTo(o.string);
    }*/
}
