package com.kosenkov;

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

    public StringBuilder avgSalaryIncreaseWhenTransferringEmployeeToAnotherDepartment() {
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
