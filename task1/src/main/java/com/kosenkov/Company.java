package com.kosenkov;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * task 1 part 2
 */

public class Company {
    String companyName; //  = inputFileName

    // Map<String, Department> departmentMap;
    List<Department> departmentList;

    public Company(List<Department> departmentList, String companyName) {
        this.departmentList = departmentList;
        this.companyName = companyName;
    }

    /*public StringBuilder avgSalaryIncreaseWhenTransferringEmployeeToAnotherDepartment() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Department dep1 : departmentList) {
            for (Department dep2 : departmentList) {
                if (!dep1.getDepartmentName().equals(dep2.getDepartmentName())) {
                    for (int i = 0; i < dep1.getEmployeeList().size(); i++) {
                        stringBuilder.append(dep1.transferringEmployeeToAnotherDepartment(i, dep2));
                    }
                }
            }
        }
        return stringBuilder;
    }*/

    public StringBuilder avgSalaryIncreaseWhenTransferringEmployeeToAnotherDepartment() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Department dep1 : departmentList) {
            for (Department dep2 : departmentList) {
                if (!dep1.getDepartmentName().equals(dep2.getDepartmentName()) ||
                        dep1.getEmployeeList().size() <= 1) {

                    //  Рассматриваем для перевода только сотрудников из отдела с наивысшей средней зп,
                    //  у которых зарплата ниже или равна средней зп по отделу

                    BigDecimal avgSalaryDep1 = dep1.getAvgSalary();
                    BigDecimal avgSalaryDep2 = dep2.getAvgSalary();

                    if (dep1.getAvgSalary().compareTo(dep2.getAvgSalary()) > 0) {

                        List<Employee> employeeListDep1 = dep1.getEmployeeList();

                        for (int i = 0; i < employeeListDep1.size(); i++) {
                            BigDecimal emplDep1Salary = employeeListDep1.get(i).getSalary();
                            if (emplDep1Salary.compareTo(avgSalaryDep1) < 0 && emplDep1Salary.compareTo(avgSalaryDep2) > 0) {
                                stringBuilder.append(dep1.transferringEmployeeToAnotherDepartment(i, dep2));
                            }
                        }
                    }
                }
            }
        }
        return stringBuilder;
    }

    /* Вывести среднюю зарплату по всем отделам компании */
    public void averageSalaryPerDepartments() {
        System.out.println(this.companyName);
        this.departmentList.forEach(dep -> System.out.println(dep.getDepartmentName() + " AvgSalary: " + dep.getAvgSalary()));
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder(Colour.ANSI_BLUE + "Название компании: " + companyName + Colour.ANSI_RESET + "\n");

        stringBuilder.append("Количество департаментов: ").append(departmentList.size()).append("\n");

        long numberOfEmployees = 0;
        for (Department dep : departmentList) {
            numberOfEmployees += dep.getEmployeeList().size();
        }
        stringBuilder.append("Количество работников: ").append(numberOfEmployees).append("\n\n");

        int i = 0;
        for (Department dep : departmentList) {
            stringBuilder.append(++i).append(" : ").append(dep.toString()).append("\n");
        }
        stringBuilder.append(Colour.ANSI_BLUE + "Конец информации о компании: ").append(companyName).append(Colour.ANSI_RESET);
        return stringBuilder.toString();
    }
}
