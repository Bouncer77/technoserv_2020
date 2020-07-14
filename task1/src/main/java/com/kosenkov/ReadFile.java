package com.kosenkov;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.math.BigDecimal.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * task 1 part 2
 */

public class ReadFile {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static Company readFile(String fileName) {
        List<Department> departmentList = new LinkedList<>();
        Company company = null;
        try (final Scanner scanner = new Scanner(new File(fileName))) {
            int i = 0;
            String fioLine = "";
            String salaryLine = "0.0";
            BigDecimal salary = BigDecimal.valueOf(0);
            String departmentName = "";
            int numberEmployees = 0;
            while (scanner.hasNextLine()) {
                switch (i) {
                    case 0:
                        // fio
                        fioLine = scanner.nextLine();
                        i++;
                        break;
                    case 1:
                        // salary
                        salaryLine = scanner.nextLine();
                        i++;
                        break;
                    case 2:
                        // depName
                        departmentName = scanner.nextLine();
                        i++;
                        break;
                    case 3:
                        // save
                        String[] fio = fioLine.split(" ");
                        if (!validationFio(fio) ||
                                !validationDepartmentName(departmentName) ||
                                !validationSalary(salaryLine)) {
                            scanner.nextLine();
                            i = 0;
                            break;
                        }
                        salary = BigDecimal.valueOf(Double.parseDouble(salaryLine)).setScale(2, ROUND_CEILING);
                        Employee employee = new Employee(fio[0], fio[1],
                                fio.length == 3 ? fio[2] : "",
                                salary);
                        Department.addToDepartment(employee,
                                departmentName, departmentList);
                        i = 0;
                        ++numberEmployees;
                        scanner.nextLine();
                        break;
                    default:
                        i = 0;
                }
            }
            company = new Company(departmentList, fileName);
            System.out.println("Сотрудников добавлено: " + numberEmployees);
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден!");
        }

        System.out.println("\nDep List:");
        System.out.println(departmentList);
        System.out.println("\nEmployees:");

        return company;
    }

    private static boolean validationSalary(String salaryLine) {
        if (salaryLine.matches("\\d+\\.\\d*")) {
            return true;
        } else {
            System.out.println(ANSI_YELLOW + "Предупреждение!" + ANSI_RESET + " Отброшен сотрудник с зарплатой: " + salaryLine);
            return false;
        }
    }

    private static boolean validationDepartmentName(String depName) {
        if (depName.matches("[а-яА-Я ]+") || depName.matches("[a-zA-Z ]+") || depName.isEmpty()) {
            return true;
        } else {
            System.out.println(ANSI_YELLOW + "Предупреждение!" + ANSI_RESET + " Отброшен сотрудник с именем отдела: " + depName);
            return false;
        }
    }

    public static boolean validationFio(String[] fio) {
        if (fio.length > 3 || fio.length < 2) {
            return false;
        }

        int i = 0;
        for (String str : fio) {

            if (str.matches("[а-яА-Я]+") || str.matches("[a-zA-Z]+")) {
                ++i;
            }
        }

        if (i == fio.length)
            return true;

        System.out.println(ANSI_YELLOW + "Предупреждение!" + ANSI_RESET + " Отброшен сотрудник с ФИО: " +
                Arrays.toString(fio) + "  - так как его имя не соответствует регулярному выражению");
        return false;
    }

    public static void validationInputArguments(String[] args) {
        // Проверка на существование файла
        if (args.length < 2) {
            System.out.println("Недостаточно входных аргументов");
            System.out.println("task1 <название_входного_файла> <название_выходного_файла>");
            System.exit(1);
        }

        if (!fileExists(args[0])) {
            System.out.println("Файла с именем " + args[0] + " не существует");
            System.exit(2);
        }

        if (!fileExists(args[1])) {
            System.out.println("Файла с именем " + args[1] + " не существует");
            System.exit(3);
        }
    }

    private static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
