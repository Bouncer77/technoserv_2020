package com.kosenkov;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Kosenkov Ivan on 13.07.2020
 * Класс описывающий компанию
 * @see com.kosenkov.Department
 * */
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

        for (Department dep1 : departmentMap.values()) {
            for (Department dep2 : departmentMap.values()) {
                if (!dep1.getDepartmentName().equals(dep2.getDepartmentName()) ||
                    dep1.getEmployeeList().size() <= 1) {

                    BigDecimal avgSalaryDep1 = dep1.getAvgSalary();
                    BigDecimal avgSalaryDep2 = dep2.getAvgSalary();

                    if (avgSalaryDep1.compareTo(avgSalaryDep2) > 0) {

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

    public StringBuilder getAllGroupEmployeeForTransfer() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Department dep1 : departmentMap.values()) {

            // Все группы
            List<int[]> groupList = new ArrayList<>();
            for (int i = 0; i < dep1.getEmployeeList().size(); i++) {
                groupList.addAll(dep1.combinationsWithoutRepetitionsAndAvgGroupSalaryLessThanAvgInDep(i + 1));
            }

            for (Department dep2 : departmentMap.values()) {
                if (!dep1.getDepartmentName().equals(dep2.getDepartmentName()) ||
                        dep1.getEmployeeList().size() <= 1) {

                    BigDecimal avgSalaryDep1 = dep1.getAvgSalary();
                    BigDecimal avgSalaryDep2 = dep2.getAvgSalary();

                    if (avgSalaryDep1.compareTo(avgSalaryDep2) > 0) {

                        //System.out.println("All Group List");
                        //System.out.println(MyPrint.printGroupList(groupList));

                        List<Employee> employeeList = dep1.getEmployeeList();

                        stringBuilder.append("Из ").append(dep1.getDepartmentName()).append(" в ").append(dep2.getDepartmentName()).append("\n");
                        stringBuilder.append("Средняя зарплата до перевода группы работников:\n");
                        stringBuilder.append(dep1.getDepartmentName()).append(" : ").append(avgSalaryDep1).append("\n");
                        stringBuilder.append(dep2.getDepartmentName()).append(" : ").append(avgSalaryDep2).append("\n\n");
                        int groupNum = 1;
                        for (int[] group : groupList) {
                            BigDecimal avgGroupSalary = dep1.getAvgGroupSalary(group);
                            if (avgGroupSalary.compareTo(avgSalaryDep2) > 0 && avgGroupSalary.compareTo(avgSalaryDep1) < 0) {
                                stringBuilder.append("Группа: ").append(groupNum++).append("\n");
                                //stringBuilder.append("Средняя зп по группе: ").append(avgGroupSalary).append("\n");
                                BigDecimal groupSumSalary = BigDecimal.valueOf(0.0);
                                for (int value : group) {
                                    Employee employee = employeeList.get(value - 1);
                                    stringBuilder.append(employee.getFio()).append(" ").append(employee.getSalary()).append("\n");
                                    groupSumSalary = groupSumSalary.add(employee.getSalary());
                                }
                                stringBuilder.append("\n");

                                BigDecimal avgSalaryAfterForDep1 = dep1.getSumSalaryInDepartment().subtract(groupSumSalary).
                                        divide(BigDecimal.valueOf(dep1.getEmployeeList().size() - group.length), 2, RoundingMode.HALF_UP);

                                BigDecimal avgSalaryAfterForDep2 = dep2.getSumSalaryInDepartment().add(groupSumSalary).
                                        divide(BigDecimal.valueOf(dep2.getEmployeeList().size() + group.length), 2, RoundingMode.HALF_UP);
                                stringBuilder.append(dep1.getDepartmentName()).append(" : ").append(avgSalaryAfterForDep1).append("\n");
                                stringBuilder.append(dep2.getDepartmentName()).append(" : ").append(avgSalaryAfterForDep2).append("\n\n\n");
                            }
                        }
                        stringBuilder.append("\n");
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
        for (Department dep : departmentMap.values()) {
            numberOfEmployees += dep.getEmployeeList().size();
            stringBuilder.append(++i).append(" : ").append(dep.toString()).append("\n");
        }
        stringBuilder.append("Количество работников: ").append(numberOfEmployees).append("\n\n");

        stringBuilder.append(Colour.ANSI_BLUE + "Конец информации о компании: ").append(companyName).append(Colour.ANSI_RESET);
        return stringBuilder.toString();
    }
}
