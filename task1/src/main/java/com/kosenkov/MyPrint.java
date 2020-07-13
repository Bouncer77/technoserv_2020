package com.kosenkov;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * task 1 part 2
 */

public class MyPrint {
    public static void printArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
        System.out.println();
    }

    public static void averageSalaryPerDepartments(Company company) {
        List<Department> departmentList = company.departmentList;
        departmentList.forEach(dep -> System.out.println(dep.getDepartmentName() + " AvgSalary: " + dep.getAvgSalary()));
    }

    public static void printWhenAvgSalaryIncreases(Company company, String fileName) {
        StringBuilder stringBuilder = new StringBuilder("***************************************\n" +
                new Date().toString() + "\n***************************************\n");
        stringBuilder.append(company.avgSalaryIncreaseWhenTransferringEmployeeToAnotherDepartment());

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("Файл является каталогом или не может быть создан, или открыт");
        }
    }
}
