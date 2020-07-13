package com.kosenkov;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public List<Employee> getEmployeeList() {
        return employeeList;
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

    public String transferringEmployeeToAnotherDepartment(int employeeNum, Department depTo) {
        BigDecimal fromDepAvgSalaryBefore = this.getAvgSalary();
        BigDecimal toDepAvgSalaryBefore = depTo.getAvgSalary();
        Employee employee = employeeList.get(employeeNum);

        BigDecimal fromDepAvgSalaryAfter = this.getAvgSalaryV2(
                getSumSalaryInDepartment().subtract(employee.getSalary()), this.employeeList.size() - 1);

        BigDecimal toDepAvgSalaryAfter = depTo.getAvgSalaryV2(
                depTo.getSumSalaryInDepartment().add(employee.getSalary()), depTo.employeeList.size() + 1);

        /* средняя зарплата увеличивается в обоих отделах */
        if ((fromDepAvgSalaryBefore.compareTo(fromDepAvgSalaryAfter) == -1) &&
                (toDepAvgSalaryBefore.compareTo(toDepAvgSalaryAfter) == -1)) {

            return departmentName + " --- " + employee.getFirstName() + " " + employee.getLastName()
                    + " -->>> " + depTo.getDepartmentName() +
                    "\nbefore: " + fromDepAvgSalaryBefore + " | " + toDepAvgSalaryBefore +
                    "\nafter: " + fromDepAvgSalaryAfter + " | " + toDepAvgSalaryAfter + "\n\n";
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n" + departmentName + "\n");
        for (Employee em : employeeList) {
            stringBuilder.append(em.getFirstName()).append(" ").append(em.getLastName()).
                    append(" Salary: ").append(em.getSalary()).append("\n");
        }
        return stringBuilder.toString();
    }
}
