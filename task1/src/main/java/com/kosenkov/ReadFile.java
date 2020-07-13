package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_DOWN;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * lesson
 */

public class ReadFile {
    public static Company readFile(String fileName) {
        List<Department> departmentList = new LinkedList<>();
        List<Employee> employeeList = new LinkedList<>();
        Company company = null;
        try(final Scanner scanner = new Scanner(new File(fileName))) {
            int i = 0;
            String fioLine = "";
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
                        String salaryLine = scanner.nextLine();
                        i++;
                        salary = BigDecimal.valueOf(Double.parseDouble(salaryLine)).setScale(2, ROUND_DOWN);
                        break;
                    case 2:
                        // depName
                        departmentName = scanner.nextLine();
                        i++;
                        break;
                    case 3:
                        // save
                        String[] fio = fioLine.split(" ");
                        Employee employee = new Employee(fio[0], fio[1],
                                fio.length == 3? fio[2] : "",
                                salary);
                        employeeList.add(employee);
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
            company = new Company(departmentList, employeeList);
            System.out.println("Сотрудников добавлено: " + numberEmployees);
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден!");
        }

        System.out.println("\nDep List:");
        System.out.println(departmentList);
        System.out.println("\nEmployees:");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        return company;
    }
}
