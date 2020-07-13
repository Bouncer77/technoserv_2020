package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * lesson
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
        departmentList.forEach(dep -> System.out.println(dep.getDepartmentId() + " : " +
                dep.getDepartmentName() + " AvgSalary: " + dep.takeAvgSalary()));
    }

    public static void printWhenAvgSalaryIncreases(Company company, String fileName) {
        Department.whenAvgSalaryIncreases(company.departmentList, fileName);
    }
}
