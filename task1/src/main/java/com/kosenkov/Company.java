package com.kosenkov;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * task 1 part 3
 */

public class Company {

    String companyName; //  = inputFileName
    Map<String, Department> departmentMap;

    public Company(String companyName) {
        this.companyName = companyName;
        this.departmentMap = new HashMap<>();
    }

    public Company(String companyName, Map<String, Department> departmentMap) {
        this.companyName = companyName;
        this.departmentMap = departmentMap;
    }

    public StringBuilder avgSalaryIncreaseWhenTransferringEmployeeToAnotherDepartment() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Department> entryDep1 : departmentMap.entrySet()) {
            for (Map.Entry<String, Department> entryDep2 : departmentMap.entrySet()) {
                if (!entryDep1.getKey().equals(entryDep2.getKey()) ||
                    entryDep1.getValue().getEmployeeList().size() <= 1) {

                    BigDecimal avgSalaryDep1 = entryDep1.getValue().getAvgSalary();
                    BigDecimal avgSalaryDep2 = entryDep2.getValue().getAvgSalary();

                    if (entryDep1.getValue().getAvgSalary().compareTo(entryDep2.getValue().getAvgSalary()) > 0) {

                        List<Employee> employeeListDep1 = entryDep1.getValue().getEmployeeList();

                        for (int i = 0; i < employeeListDep1.size(); i++) {
                            BigDecimal emplDep1Salary = employeeListDep1.get(i).getSalary();
                            if (emplDep1Salary.compareTo(avgSalaryDep1) < 0 && emplDep1Salary.compareTo(avgSalaryDep2) > 0) {
                                stringBuilder.append(entryDep1.getValue().transferringEmployeeToAnotherDepartment(i, entryDep2.getValue()));
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
        this.departmentMap.forEach((depName, dep) -> System.out.println(depName + " | Средняя зарплата: " + dep.getAvgSalary()));
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder(Colour.ANSI_BLUE + "Название компании: " + companyName + Colour.ANSI_RESET + "\n");

        stringBuilder.append("Количество департаментов: ").append(departmentMap.size()).append("\n");

        long numberOfEmployees = 0;

        int i = 0;
        for (Map.Entry<String, Department> entry : departmentMap.entrySet()) {
            numberOfEmployees += entry.getValue().getEmployeeList().size();
            stringBuilder.append(++i).append(" : ").append(entry.getValue().toString()).append("\n");
        }
        stringBuilder.append("Количество работников: ").append(numberOfEmployees).append("\n\n");

        stringBuilder.append(Colour.ANSI_BLUE + "Конец информации о компании: ").append(companyName).append(Colour.ANSI_RESET);
        return stringBuilder.toString();
    }
}
