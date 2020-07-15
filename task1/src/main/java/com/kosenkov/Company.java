package com.kosenkov;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
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

                    if (avgSalaryDep1.compareTo(avgSalaryDep2) > 0) {

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

    public StringBuilder avgSalaryIncreaseWhenTransferringEmployeeListToAnotherDepartment() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Department> entryDep1 : departmentMap.entrySet()) {
            for (Map.Entry<String, Department> entryDep2 : departmentMap.entrySet()) {
                if (!entryDep1.getKey().equals(entryDep2.getKey()) ||
                        entryDep1.getValue().getEmployeeList().size() <= 1) {

                    BigDecimal avgSalaryDep1 = entryDep1.getValue().getAvgSalary();
                    BigDecimal avgSalaryDep2 = entryDep2.getValue().getAvgSalary();

                    if (avgSalaryDep1.compareTo(avgSalaryDep2) > 0) {

                        List<Employee> employeeListDep1 = entryDep1.getValue().getEmployeeList();

                        for (int i = 0; i < employeeListDep1.size(); ++i) {
                            BigDecimal totalSalaryGroup = BigDecimal.valueOf(0.0);
                            for (int j = 0; j <= i; ++j) {
                                totalSalaryGroup = totalSalaryGroup.add(employeeListDep1.get(j).getSalary());
                            }

                            BigDecimal avgGroupSalary = totalSalaryGroup.divide(new BigDecimal(i + 1), 2, RoundingMode.CEILING);
                            if (avgGroupSalary.compareTo(avgSalaryDep1) < 0) {
                                BigDecimal totalSalaryDep2 = entryDep2.getValue().getSumSalaryInDepartment();
                                totalSalaryDep2 = totalSalaryDep2.add(totalSalaryGroup); // dep2 + group
                                long numEmplDep2 = entryDep2.getValue().getEmployeeList().size() + i + 1;

                                BigDecimal avgSalaryAfterDep2 = totalSalaryDep2.divide(BigDecimal.valueOf(numEmplDep2), 2, RoundingMode.CEILING);

                                BigDecimal totalSalaryDep1 = BigDecimal.valueOf(0.0);
                                for (Employee em : employeeListDep1) {
                                    totalSalaryDep1 = totalSalaryDep1.add(em.getSalary());
                                }
                                totalSalaryDep1 = totalSalaryDep1.subtract(totalSalaryGroup);
                                long numEmplDep1 = employeeListDep1.size() - (i + 1);
                                BigDecimal avgSalaryAfterDep1 = totalSalaryDep1.divide(BigDecimal.valueOf(numEmplDep1), 2, RoundingMode.CEILING);

                                if (avgSalaryAfterDep1.compareTo(avgSalaryDep1) > 0 &&
                                    avgSalaryAfterDep2.compareTo(avgSalaryDep2) > 0) {
                                    stringBuilder.append("Из ").append(entryDep1.getKey()).append("\n");
                                    stringBuilder.append("В ").append(entryDep2.getKey()).append("\n");
                                    for (int m = 0; m <= i; ++i) {
                                        stringBuilder.append(employeeListDep1.get(m).getFio()).append(", ");
                                    }
                                    stringBuilder.append("\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        return stringBuilder;
    }

    /* Вывести среднюю зарплату по всем отделам компании */
    public void printAverageSalaryPerDepartments() {
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
