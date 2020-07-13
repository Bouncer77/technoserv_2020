package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */

public class Department {

    private String departmentName;
    private List<Employee> employeeList;

    private static final Map<Integer, String> departmentMap = new HashMap<>();
    static {
        departmentMap.put(1, "United States Department of Education");
        departmentMap.put(2, "Russian Development department");
    }

    // new
    public Department(String departmentName, Employee employee) {
        this.departmentName = departmentName;
        this.employeeList = new LinkedList<>();
        employeeList.add(employee);
    }

    // new
    public void addEmployeeToDepartment(Employee employee) {
        this.employeeList.add(employee);
    }

    // new
    public static void addToDepartment(Employee employee, String departmentName, List<Department> departmentList) {
        if (hasDepartment(departmentName, departmentList)) {
            addEmployeeToDepartment(departmentName, departmentList, employee);
        } else {
            departmentList.add(new Department(departmentName, employee));
        }
    }

    // new
    public static boolean hasDepartment(String departmentName, List<Department> departmentList) {
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(departmentName)) {
                return true;
            }
        }
        return false;
    }

    // new
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
        // return new LinkedList<>(this.employeeList);
    }

    public static String takeDepartmentNameById(int departmentId) {
        return departmentMap.getOrDefault(departmentId, "");
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
            avgSalary = avgSalary.divide(BigDecimal.valueOf(employeeList.size()));
            return avgSalary;
        }
        else {
            return BigDecimal.valueOf(0.0);
        }
    }

    private BigDecimal takeAvgSalaryOneMore(BigDecimal salary) {
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

    private BigDecimal takeAvgSalaryWithOutOne(BigDecimal salary) {
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
        for (Department dep1 : departmentList) {
            for (Department dep2 : departmentList) {
                if (!dep1.getDepartmentName().equals(dep2.getDepartmentName())) {
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
        BigDecimal fromDepAvgSalaryAfter = this.takeAvgSalaryWithOutOne(employee.getSalary());
        BigDecimal toDepAvgSalaryAfter = depTo.takeAvgSalaryOneMore(employee.getSalary());

        /*средняя зарплата увеличивается в обоих отделах*/
        /*if (fromDepAvgSalaryBefore < fromDepAvgSalaryAfter &&
                toDepAvgSalaryBefore < toDepAvgSalaryAfter) {*/
        if ((fromDepAvgSalaryBefore.compareTo(fromDepAvgSalaryAfter) == -1) && (toDepAvgSalaryBefore.compareTo(toDepAvgSalaryAfter) == -1)) {

            // Запись в файл
            try (PrintWriter pw = new PrintWriter(new File(fileName))){
                pw.println(getDepartmentName() + " --- " +
                        employee.getFirstName() + " " + employee.getLastName() + " -->>> " +
                        depTo.getDepartmentName());
                pw.println("before: " + fromDepAvgSalaryBefore + " | " + toDepAvgSalaryBefore);
                pw.println("after: " + fromDepAvgSalaryAfter + " | " + toDepAvgSalaryAfter);
            } catch (FileNotFoundException e) {
                System.out.println("Файл с именем " + fileName + " не найден!");
            }
        }
    }
}
