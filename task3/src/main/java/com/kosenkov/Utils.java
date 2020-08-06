package com.kosenkov;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 31.07.2020
 */

public class Utils {

    public static int getRandomCount(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int getRandomCount() {
        return getRandomCount(1, 10);
    }

    public static boolean validatioInputValues(String[] args) {
        if (args.length != 1) {
            System.out.println(Colour.red("Ошибка: неверное количество переданных аргументов в программу"));
            return false;
        }

        return args[0].matches("\\d+");
    }
}
