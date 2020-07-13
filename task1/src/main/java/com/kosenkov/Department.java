package com.kosenkov;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.math.BigDecimal.ROUND_CEILING;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */

public class Department {

    private String departmentName;
    private List<Employee> employeeList;

    public Department(String departmentName, Employee employee) {
        this.departmentName = departmentName;
        this.employeeList = new LinkedList<>();
        employeeList.add(employee);
    }

    public static void addToDepartment(Employee employee, String departmentName, List<Department> departmentList) {

        boolean isDepList = false;
        for (Department dep : departmentList) {
            if (departmentName.equals(dep.departmentName)) {
                isDepList = true;
                dep.employeeList.add(employee);
                break;
            }
        }
        if (!isDepList) {
            departmentList.add(new Department(departmentName, employee));
        }
    }

    public String getDepartmentName() {
        return departmentName;
    }

    /* Рассчитать среднюю зарплату в отделе */
    public BigDecimal getAvgSalary() {
        return this.getAvgSalaryV2(this.getSumSalaryInDepartment(), this.employeeList.size());
    }

    /* Сумма всех зарплат в отделе */
    public BigDecimal getSumSalaryInDepartment() {
        BigDecimal sumSalary = BigDecimal.valueOf(0.0);
        for (Employee em : employeeList) {
            sumSalary = sumSalary.add(em.getSalary());
        }
        return sumSalary;
    }

    /* Средняя зарплата по отделу */
    public BigDecimal getAvgSalaryV2(BigDecimal sumSalary, long num) {

        if (num == 0) {
            return BigDecimal.valueOf(0.0);
        }

        if (num > 0) {
            if (sumSalary.compareTo(new BigDecimal("0.0")) != -1) {
                return sumSalary.divide(BigDecimal.valueOf(num), 2, RoundingMode.CEILING);
            } else {
                throw new CompanySalaryCalculatorException("Сумма зарплат оказалась меньше нуля: " + sumSalary.toString());
            }
        } else {
            throw new CompanySalaryCalculatorException("Число работников при подсчете средней зарплаты оказалось равным " + num);
        }
    }

    public static void whenAvgSalaryIncreases(List<Department> departmentList, String fileName) {
        System.out.println(departmentList);
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.println("***************************************");
            pw.println(new Date().toString());
            pw.println("***************************************");
        } catch (IOException e) {
            System.out.println("Файл является каталогом или не может быть создан, или открыт");
        }
        for (Department dep1 : departmentList) {
            for (Department dep2 : departmentList) {
                if (!dep1.getDepartmentName().equals(dep2.getDepartmentName())) {
                    // System.out.println(dep1.departmentName + " -> " + dep2.departmentName);
                    for (int i = 0; i < dep1.employeeList.size(); i++) {
                        dep1.averageSalaryIncreasesInBothDepartments(i, dep2, fileName);
                    }
                }
            }
        }
    }

    private void averageSalaryIncreasesInBothDepartments(int employeeNum, Department depTo, String fileName) {
        BigDecimal fromDepAvgSalaryBefore = this.getAvgSalary();
        BigDecimal toDepAvgSalaryBefore = depTo.getAvgSalary();
        Employee employee = employeeList.get(employeeNum);

        BigDecimal fromDepAvgSalaryAfter = this.getAvgSalaryV2(
                getSumSalaryInDepartment().subtract(employee.getSalary()), this.employeeList.size() - 1);

        BigDecimal toDepAvgSalaryAfter = depTo.getAvgSalaryV2(
                depTo.getSumSalaryInDepartment().add(employee.getSalary()), depTo.employeeList.size() + 1);

        /*средняя зарплата увеличивается в обоих отделах*/
       System.out.println(this.departmentName + " : " + fromDepAvgSalaryBefore + " -> " + fromDepAvgSalaryAfter);
        System.out.println(depTo.departmentName + " : " + toDepAvgSalaryBefore + " -> " + toDepAvgSalaryAfter);
        if ((fromDepAvgSalaryBefore.compareTo(fromDepAvgSalaryAfter) == -1) &&
                (toDepAvgSalaryBefore.compareTo(toDepAvgSalaryAfter) == -1)) {

            System.out.println(employee.getFirstName() + " " + employee.getLastName());

            // Запись в файл
            try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
                pw.println(getDepartmentName() + " --- " +
                        employee.getFirstName() + " " + employee.getLastName() + " -->>> " +
                        depTo.getDepartmentName());
                pw.println("before: " + fromDepAvgSalaryBefore + " | " + toDepAvgSalaryBefore);
                pw.println("after: " + fromDepAvgSalaryAfter + " | " + toDepAvgSalaryAfter);
                pw.println();
            } catch (FileNotFoundException e) {
                System.out.println("Файл с именем " + fileName + " не найден!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n"+ departmentName + "\n");
        for (Employee em : employeeList) {
            stringBuilder.append(em.getFirstName()).append(" ").append(em.getLastName()).
                    append(" Salary: ").append(em.getSalary()).append("\n");
        }
        return stringBuilder.toString();
    }
}
