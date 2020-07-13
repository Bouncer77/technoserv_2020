package com.kosenkov;

import java.io.*;
import java.util.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class ReadEmployee {

    public static void main(String[] args) {

        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))){
            @SuppressWarnings("unchecked")
            List<Employee> employeeList = (List<Employee>) ois.readObject();

            employeeList.forEach(System.out::println);
            List<Department> departmentList = Department.createDepartmentListFromEmployeeList(employeeList);
            System.out.println(departmentList);

            averageSalaryPerDepartments(departmentList);

           // Department.whenAvgSalaryIncreases(departmentList);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void averageSalaryPerDepartments(List<Department> departmentList) {
        departmentList.forEach(dep -> System.out.println(dep.getDepartmentId() + " : " +
                dep.getDepartmentName() + " AvgSalary: " + dep.takeAvgSalary()));
    }
}
