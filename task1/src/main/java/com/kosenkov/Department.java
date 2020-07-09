package com.kosenkov;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * lesson
 */

public class Department {

    private int departmentId;
    private String departmentName;
    private List<Employee> employeeList;

    private static final Map<Integer, String> departmentMap = new HashMap<>();
    static {
        departmentMap.put(1, "United States Department of Education");
        departmentMap.put(2, "Russian Development department");
    }

    public Department(int departmentId, String departmentName, List<Employee> employeeList) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeList = employeeList;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public static String takeDepartmentNameById(int departmentId) {
        return departmentMap.getOrDefault(departmentId, "");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n" + departmentId + " : " + departmentName + "\n");
        for (Employee em : employeeList) {
            stringBuilder.append(em.getFirstName()).append(" ").append(em.getLastName()).append("\n");
        }
        return stringBuilder.toString();
    }

    public double takeAvgSalary() {
        double avgSalary = 0.0;
        for (Employee em : employeeList) {
            avgSalary += em.getSalary();
        }
        avgSalary /= employeeList.size();
        return avgSalary;
    }
}
