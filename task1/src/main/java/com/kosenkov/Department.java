package com.kosenkov;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
            if (departmentName.equals(dep.getDepartmentName())) {
                isDepList = true;
                addEmployeeToDepartment(departmentName, departmentList, employee);
                break;
            }
        }
        if (!isDepList) {
            departmentList.add(new Department(departmentName, employee));
        }
    }

    public static boolean hasDepartment(String departmentName, List<Department> departmentList) {
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(departmentName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean addEmployeeToDepartment(String departmentName, List<Department> departmentList, Employee employee) {
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(departmentName)) {
                dep.getEmployeeList().add(employee);
                return true;
            }
        }
        return false;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    private List<Employee> getEmployeeList() {
        return this.employeeList;
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

    public BigDecimal getAvgSalary() {
        BigDecimal avgSalary = BigDecimal.valueOf(0.0);
        for (Employee em : employeeList) {
            avgSalary = avgSalary.add(em.getSalary());
        }

        if (!employeeList.isEmpty()) {
            avgSalary = avgSalary.divide(BigDecimal.valueOf(employeeList.size()), 2, RoundingMode.HALF_UP);
            return avgSalary;
        }
        else {
            return BigDecimal.valueOf(0.0);
        }
    }

    private BigDecimal getAvgSalaryOneMore(BigDecimal salary) {
        BigDecimal avgSalary = new BigDecimal(salary.toString());
        for (Employee em : employeeList) {
            avgSalary = avgSalary.add(em.getSalary());
        }

        if (!employeeList.isEmpty()) {
            avgSalary = avgSalary.divide(BigDecimal.valueOf(employeeList.size() + 1), 2);
            return avgSalary;
        }
        else {
            return BigDecimal.valueOf(0.0);
        }
    }

    private BigDecimal getAvgSalaryWithOutOne(BigDecimal salary) {
        BigDecimal avgSalary = new BigDecimal("-" + salary.toString());
        for (Employee em : employeeList) {
            avgSalary = avgSalary.add(em.getSalary());
        }

        if (employeeList.size() > 1) {
            avgSalary = avgSalary.divide(BigDecimal.valueOf(employeeList.size() -1), 2);
            return avgSalary;
        }
        else {
            return BigDecimal.valueOf(0.0);
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
                    for (int i = 0; i < dep1.getEmployeeList().size(); i++) {
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
        BigDecimal fromDepAvgSalaryAfter = this.getAvgSalaryWithOutOne(employee.getSalary());
        BigDecimal toDepAvgSalaryAfter = depTo.getAvgSalaryOneMore(employee.getSalary());

        /*средняя зарплата увеличивается в обоих отделах*/
        /*if (fromDepAvgSalaryBefore < fromDepAvgSalaryAfter &&
                toDepAvgSalaryBefore < toDepAvgSalaryAfter) {*/
        System.out.println(this.departmentName + " : " + fromDepAvgSalaryBefore + " -> " + fromDepAvgSalaryAfter);
        System.out.println(depTo.departmentName + " : " + toDepAvgSalaryBefore + " -> " + toDepAvgSalaryAfter);
        if ((fromDepAvgSalaryBefore.compareTo(fromDepAvgSalaryAfter) == -1) && (toDepAvgSalaryBefore.compareTo(toDepAvgSalaryAfter) == -1)) {

            System.out.println(employee.getFirstName() + " " + employee.getLastName());

            // Запись в файл
            // try (PrintWriter pw = new PrintWriter(new File(fileName))){
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
}
