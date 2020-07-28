package com.kosenkov;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Kosenkov Ivan on 13.07.2020
 * Класс обеспечивает вывод информации во внешний файл или стандартный поток вывода
 * */
public class MyPrint {

    /**
     * Вывести в стандартный поток вывода аргументы main метода
     * */
    public static void printArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
        System.out.println();
    }

    /**
     * Выводит в файл все варианты возможных переводов сотрудников из одного отдела в другой,
     * при которых средняя зарплата увеличивается в обоих отделах
     * */
    public static void printWhenAvgSalaryIncreasesForAllGroups(Company company, String fileName) {

        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateAndTime = localDateTime.format(formatter);

        StringBuilder stringBuilder = new StringBuilder("***************************************\n" +
                 dateAndTime + "\n***************************************\n");
        stringBuilder.append(company.getAllGroupEmployeeForTransfer());

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("Файл является каталогом или не может быть создан, или открыт");
        }
    }

    public static String printWarning() {
        return Colour.ANSI_YELLOW + "Предупреждение!" + Colour.ANSI_RESET;
    }

    public static String printWarning(String msg) {
        return Colour.ANSI_YELLOW + msg + Colour.ANSI_RESET;
    }
}
