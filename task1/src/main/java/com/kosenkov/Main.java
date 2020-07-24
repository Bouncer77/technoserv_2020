package com.kosenkov;

/**
 * Первая задача в процессе стажировки в компании Иннотехнум
 * Выводит в файл список возможных переводов сотрудников из одного отдела в любой другой , при котором
 * средняя зарплата по отделам растет
 *
 * @author Kosenkov Ivan 13.07.2020
 * @version 1.0.0
 */
public class Main {

    public static final int INPUT_FILE_ARGS_INDEX = 0;
    public static final int OUTPUT_FILE_ARGS_INDEX = 1;

    /** Точка входа в класс и приложение
     * @param args Массив строковых аргументов,
     *             agrs[0] входной файл в .csv формате,
     *             args[1] выходной файл
     * */
    public static void main(String[] args) {
        ReadFile.validationInputArguments(args);
        MyPrint.printArgs(args);
        Company company = ReadFile.readFile(args[INPUT_FILE_ARGS_INDEX]);
        System.out.println(company);
        MyPrint.printWhenAvgSalaryIncreasesForAllGroups(company, args[OUTPUT_FILE_ARGS_INDEX]);
    }
}
