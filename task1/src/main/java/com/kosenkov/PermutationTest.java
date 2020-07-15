package com.kosenkov;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 15.07.2020
 * lesson
 */

public class PermutationTest {

    // https://javatalks.ru/topics/38521
    // Вариант с использованием побитовой маски для генерации всех перестановок.
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int mask = 0;
        int limit = 1 << array.length;
        while (mask < limit) {
            printPermutation(mask, array);
            mask++;
        }
    }

    private static void printPermutation(int mask, int[] array) {
        System.out.print("(");
        for (int i = 0; i < array.length; i++) {
            //исли бит номер i еденичный, печатаем i-й элемент массива
            if ((mask & (1 << i)) != 0) {
                System.out.print(array[i] + ",");
            }
        }
        System.out.println(")");
    }
}
