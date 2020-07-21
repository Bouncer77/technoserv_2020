package com.kosenkov;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

    public StringBuilder getAllGroupEmployeeForTransfer() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Department> entryDep1 : departmentMap.entrySet()) {
            for (Map.Entry<String, Department> entryDep2 : departmentMap.entrySet()) {
                if (!entryDep1.getKey().equals(entryDep2.getKey()) ||
                        entryDep1.getValue().getEmployeeList().size() <= 1) {

                    BigDecimal avgSalaryDep1 = entryDep1.getValue().getAvgSalary();
                    BigDecimal avgSalaryDep2 = entryDep2.getValue().getAvgSalary();

                    if (avgSalaryDep1.compareTo(avgSalaryDep2) > 0) {

                        List<int[]> groupList = new ArrayList<>();

                        // Все группы
                        for (int i = 0; i < departmentMap.size() + 1; i++) {
                            groupList.addAll(entryDep1.getValue().combinationsWithoutRepetitions(i + 1));
                        }

                        System.out.println("All Group List");
                        System.out.println(MyPrint.printGroupList(groupList));

                        // Группы при переводе которых средняя зп увеличивается в двух отделах
                        List<int[]> growthAvgSalaryGroupList = new ArrayList<>();
                        List<Employee> employeeListDep1 = entryDep1.getValue().getEmployeeList();
                        for (int[] group : groupList) {
                            BigDecimal sumSalaryInGroup = BigDecimal.valueOf(0.0);
                            for (int value : group) {
                                 sumSalaryInGroup = sumSalaryInGroup.add(employeeListDep1.get(value - 1).getSalary());
                            }
                            BigDecimal avgSalaryInGroup = sumSalaryInGroup.divide(BigDecimal.valueOf(group.length), 2, RoundingMode.HALF_UP);

                            if (avgSalaryInGroup.compareTo(avgSalaryDep1) < 0 && avgSalaryInGroup.compareTo(avgSalaryDep2) > 0) {
                                growthAvgSalaryGroupList.add(group);
                            }
                        }

                        System.out.println("Growth Group List");
                        System.out.println(MyPrint.printGroupList(growthAvgSalaryGroupList));

                        //System.out.println("Group List: " + groupList);

                        List<Employee> employeeList = entryDep1.getValue().getEmployeeList();

                        stringBuilder.append("Из ").append(entryDep1.getKey()).append(" в ").append(entryDep2.getKey()).append("\n");
                        stringBuilder.append("Средняя зарплата до перевода группы работников:\n");
                        stringBuilder.append(entryDep1.getKey()).append(" : ").append(avgSalaryDep1).append("\n");
                        stringBuilder.append(entryDep2.getKey()).append(" : ").append(avgSalaryDep2).append("\n\n");
                        int groupNum = 1;
                        for (int[] group : growthAvgSalaryGroupList) {
                            stringBuilder.append("Группа: ").append(groupNum++).append("\n");
                            BigDecimal groupSumSalary = BigDecimal.valueOf(0.0);
                            for (int value : group) {
                                Employee employee = employeeList.get(value - 1);
                                stringBuilder.append(employee.getFio()).append(" ").append(employee.getSalary()).append("\n");
                                groupSumSalary = groupSumSalary.add(employee.getSalary());
                            }
                            stringBuilder.append("\n");

                            BigDecimal avgSalaryAfterForDep1 = entryDep1.getValue().getSumSalaryInDepartment().subtract(groupSumSalary).
                                    divide(BigDecimal.valueOf(entryDep1.getValue().getEmployeeList().size() - group.length), 2, RoundingMode.HALF_UP);

                            BigDecimal avgSalaryAfterForDep2 = entryDep2.getValue().getSumSalaryInDepartment().add(groupSumSalary).
                                    divide(BigDecimal.valueOf(entryDep2.getValue().getEmployeeList().size() + group.length), 2, RoundingMode.HALF_UP);
                            stringBuilder.append(entryDep1.getKey()).append(" : ").append(avgSalaryAfterForDep1).append("\n");
                            stringBuilder.append(entryDep2.getKey()).append(" : ").append(avgSalaryAfterForDep2).append("\n\n\n");
                        }
                        stringBuilder.append("\n");
                        //groupMap.put(entryDep1.getKey(), groupList);


                    }
                }
            }
        }
        return stringBuilder;
    }
    
//    public StringBuilder avgSalaryIncreaseWhenTransferringEmployeeListToAnotherDepartment() {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        Map<String, List<int[]>> groupMap = getAllGroupEmployeeForTransfer();
//
//        for (Map.Entry<String, List<int[]>> dep : groupMap.entrySet()) {
//            stringBuilder.append("Dep: ").append(dep.getKey()).append("\n");
//            for (int[] group : dep.getValue()) {
//                List<Employee> employeeList = departmentMap.get(dep.getKey()).getEmployeeList();
//                for (int i = 0; i < group.length; i++) {\
//                    Employee employee = employeeList.get(group[i] - 1);
//                    stringBuilder.append(employee.getFio() + " " + employee.getSalary() + "\n");
//                }
//            }
//        }
//
//        return stringBuilder;
//    }
    
    

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
