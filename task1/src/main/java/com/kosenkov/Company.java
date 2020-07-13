package com.kosenkov;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * lesson
 */

public class Company {
    List<Department> departmentList;
    List<Employee> employeeList;

    public Company(List<Department> departmentList, List<Employee> employeeList) {
        this.departmentList = departmentList;
        this.employeeList = employeeList;
    }
}
