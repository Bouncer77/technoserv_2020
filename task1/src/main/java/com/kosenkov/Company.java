package com.kosenkov;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * task 1 part 2
 */

public class Company {
    String companyName; //  = inputFileName
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
                if (!dep1.getDepartmentName().equals(dep2.getDepartmentName())) {

                    //  Рассматриваем для перевода только сотрудников из отдела с наивысшей средней зп,
                    //  у которых зарплата ниже или равна средней зп по отделу
                    //  (! равна средней только в том случае рассматриваем, если у всех сотрудников по отделу одна зп)

                    BigDecimal avgSalaryDep1 = dep1.getAvgSalary();
                    if (dep1.getAvgSalary().compareTo(dep2.getAvgSalary()) == 1) {

                        List<Employee> employeeListDep1 = dep1.getEmployeeList();

                        // Одного сотрудника в отделе никогда нет смысла переводить
                        // (Если зп 0.0, то это тоже не повлияет, тк средние зп по отделам должны увеличиться,
                        // а отрицательные зп недопустимы в рамках бизнес модели)
                        if (employeeListDep1.size() == 1) {
                            continue;
                        }
                        // Случай при котором все сотрудники получают одинаковую зп
                        BigDecimal salaryFistEmployee = employeeListDep1.get(0).getSalary();
                        boolean allEmployeesInDepHaveTheSameSalary = true;
                        for (int i = 1; i < employeeListDep1.size(); i++) {
                            // Если хотя бы одна зп не равна, то не у всех сотрудников в отделе одинаковые зарплаты
                            if (salaryFistEmployee.compareTo(employeeListDep1.get(i).getSalary()) != 0) {
                                allEmployeesInDepHaveTheSameSalary = false;
                                break;
                            }
                        }

                        // Если у всех сотрудников одинаковые зп
                        // То имеет смысл переводить всех сотрудников
                        if (allEmployeesInDepHaveTheSameSalary) {
                            for (int i = 0; i < employeeListDep1.size(); i++) {
                                stringBuilder.append(dep1.transferringEmployeeToAnotherDepartment(i, dep2));
                            }
                            continue; // переходим к след отделу
                        }

                        for (int i = 0; i < employeeListDep1.size(); i++) {
                            if (employeeListDep1.get(i).getSalary().compareTo(avgSalaryDep1) == -1) {
                                stringBuilder.append(dep1.transferringEmployeeToAnotherDepartment(i, dep2));
                            }
                        }
                    }
                }
            }
        }
        return stringBuilder;
    }

    /* Метод поиска отдела в списке отделов компании */
    public boolean hasDepartment(String departmentName) {
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(departmentName)) {
                return true;
            }
        }
        return false;
    }

    /* Вывести среднюю зарплату по всем отделам компании */
    public void averageSalaryPerDepartments() {
        System.out.println(this.companyName);
        this.departmentList.forEach(dep -> System.out.println(dep.getDepartmentName() + " AvgSalary: " + dep.getAvgSalary()));
    }
}
