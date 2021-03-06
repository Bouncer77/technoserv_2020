package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kosenkov Ivan on 15.07.2020
 * Класс обеспечивает чтение из файла в формате .csv без использования внешних библиотек и проверку аргументов программы
 * */
public class ReadFile {

    public static final int LAST_NAME = 0; // Фамилия
    public static final int FIRST_NAME = 1; // Имя
    public static final int SECOND_NAME = 2; // Отчество
    public static final int SALARY = 3; // Зарплата
    public static final int DEPARTMENT_NAME = 4; // Название отдела
    public static final int LAST_FIELD = DEPARTMENT_NAME; // Допустимое количество полей

    public static Company readFile(String fileName) {

        Company company = new Company(fileName.substring(0, fileName.lastIndexOf('.')), new HashMap<>());

        int lineNumber = 0;
        try (final Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ++lineNumber;
                String[] inputFields = line.split(",");

                if (inputFields.length < LAST_FIELD) {
                    System.out.println(MyPrint.printWarning() +
                            "Недостаточно информации о работнике - требуется минимум 5 полей: " +
                            "<фимилия>,<имя>,[отчество],<зарплата>,<название отдела>\n" + "Запись не принята. " + Colour.ANSI_RED +
                            lineNumber + " : " + line + Colour.ANSI_RESET + "\n");
                    continue;
                }

                for (int i = 0; i < inputFields.length; i++)
                    inputFields[i] = inputFields[i].trim();

                String[] fio = {inputFields[LAST_NAME], inputFields[FIRST_NAME], inputFields[SECOND_NAME]};
                if (!Employee.validationFio(fio) ||
                !Employee.validationSalary(inputFields[SALARY]) ||
                !Department.validationDepartmentName(inputFields[DEPARTMENT_NAME])) {
                    System.out.println(MyPrint.printWarning("Запись не принята. ") + lineNumber + " : " + line + Colour.ANSI_RESET + "\n");
                    continue;
                }

                BigDecimal salary = new BigDecimal(inputFields[SALARY]);
                Employee employee = new Employee(fio[LAST_NAME], fio[FIRST_NAME], fio[SECOND_NAME], salary);

                if (company.departmentMap.containsKey(inputFields[DEPARTMENT_NAME])) {
                    Department department = company.departmentMap.get(inputFields[DEPARTMENT_NAME]);
                    department.getEmployeeList().add(employee);
                } else {
                    company.departmentMap.put(inputFields[DEPARTMENT_NAME],
                            new Department(inputFields[DEPARTMENT_NAME], employee));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден!");
        }

        if (company.departmentMap.isEmpty()) {
            System.out.println("Не было принято ни одного сотрудника!");
            System.exit(4);
        }

        return company;
    }

    public static void validationInputArguments(String[] args) {
        // Проверка на существование файла
        if (args.length < 2) {
            System.out.println("Недостаточно входных аргументов");
            System.out.println("task1 <название_входного_файла> <название_выходного_файла>");
            System.exit(1);
        }

        // Если входного файла не существует, то программа будет завершена
        if (!new File(args[Main.INPUT_FILE_ARGS_INDEX]).exists()) {
            System.out.println("Файла с именем " + args[Main.INPUT_FILE_ARGS_INDEX] + " не существует");
            System.exit(2);
        }

        // Если файла не существует, то он будет создан
        if (!new File(args[Main.OUTPUT_FILE_ARGS_INDEX]).exists()) {
            System.out.println("Файла с именем " + args[Main.OUTPUT_FILE_ARGS_INDEX] + " не существует");
        }
    }
}
