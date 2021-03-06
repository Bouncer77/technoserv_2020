package com.kosenkov;

/**
 * @author Kosenkov Ivan on 15.07.2020
 * Класс хранит константы для цветного вывода в стандартный поток вывода
 * */
public class Colour {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String red(String msg) { return Colour.ANSI_RED + msg + Colour.ANSI_RESET; }
}
