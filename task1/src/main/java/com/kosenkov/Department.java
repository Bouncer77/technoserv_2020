package com.kosenkov;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.HashMap;
import java.util.Iterator;
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
            stringBuilder.append(em.getFirstName()).append(" ").append(em.getLastName()).
                    append(" Salary: ").append(em.getSalary()).append("\n");
        }
        return stringBuilder.toString();
    }

    public double takeAvgSalary() {
        double avgSalary = 0.0;
        for (Employee em : employeeList) {
            avgSalary += em.getSalary();
        }

        if (!employeeList.isEmpty()) {
            avgSalary /= employeeList.size();
            return avgSalary;
        }
        else {
            return 0.0;
        }
    }

    private double takeAvgSalaryOneMore(double salary) {
        double avgSalary = salary;
        for (Employee em : employeeList) {
            avgSalary += em.getSalary();
        }

        if (!employeeList.isEmpty()) {
            avgSalary /= (employeeList.size() + 1);
            return avgSalary;
        }
        else {
            return 0.0;
        }
    }

    private double takeAvgSalaryWithOutOne(double salary) {
        double avgSalary = -salary;
        for (Employee em : employeeList) {
            avgSalary += em.getSalary();
        }

        if (employeeList.size() > 1) {
            avgSalary /= (employeeList.size() - 1);
            return avgSalary;
        }
        else {
            return 0.0;
        }
    }



    public static void whenAvgSalaryIncreases(List<Department> departmentList) {
        for (Department dep1 : departmentList) {
            for (Department dep2 : departmentList) {
                if (dep1.getDepartmentId() != dep2.getDepartmentId()) {
                    for (int i = 0; i < dep1.getEmployeeList().size(); i++) {
                      dep1.averageSalaryIncreasesInBothDepartments2(i, dep2);
                    }
                }
            }
        }
    }

    public boolean averageSalaryIncreasesInBothDepartments(int employeeNum, Department depTo) {

        boolean salaryIncreasesInBothDepartments = false;
        double fromDepAvgSalaryBefore = this.takeAvgSalary();
        double toDepAvgSalaryBefore = depTo.takeAvgSalary();

        Employee employee = employeeList.get(employeeNum);

        employeeList.remove(employeeNum);
        double fromDepAvgSalaryAfter = this.takeAvgSalary();
        depTo.getEmployeeList().add(employee);
        double toDepAvgSalaryAfter = depTo.takeAvgSalary();

        /*средняя зарплата увеличивается в обоих отделах*/
        if (fromDepAvgSalaryBefore < fromDepAvgSalaryAfter &&
        toDepAvgSalaryBefore < toDepAvgSalaryAfter) {

            /*System.out.println("******************************************");
            System.out.println(this);
            System.out.println(depTo);*/


            System.out.println(this.getDepartmentId() + ":" + getDepartmentName() + " --- " +
                    employee.getFirstName() + " " + employee.getLastName() + " -->>>" + depTo.getDepartmentId() + ":" + depTo.getDepartmentName());
            System.out.println("before: " + fromDepAvgSalaryBefore + " | " + toDepAvgSalaryBefore);
            System.out.println("after: " + fromDepAvgSalaryAfter + " | " + toDepAvgSalaryAfter);
            salaryIncreasesInBothDepartments = true;

            /*System.out.println(this);
            System.out.println(depTo);
            System.out.println("******************************************");*/
        }

        /*вернуть списки в исходное состояние*/
        depTo.getEmployeeList().remove(employee);
        this.employeeList.add(employee);

        return salaryIncreasesInBothDepartments;
    }

    public boolean averageSalaryIncreasesInBothDepartments2(int employeeNum, Department depTo) {

        boolean salaryIncreasesInBothDepartments = false;
        double fromDepAvgSalaryBefore = this.takeAvgSalary();
        double toDepAvgSalaryBefore = depTo.takeAvgSalary();

        Employee employee = employeeList.get(employeeNum);

        //employeeList.remove(employeeNum);
        double fromDepAvgSalaryAfter = this.takeAvgSalaryWithOutOne(employee.getSalary());
        //depTo.getEmployeeList().add(employee);
        double toDepAvgSalaryAfter = depTo.takeAvgSalaryOneMore(employee.getSalary());

        /*средняя зарплата увеличивается в обоих отделах*/
        if (fromDepAvgSalaryBefore < fromDepAvgSalaryAfter &&
                toDepAvgSalaryBefore < toDepAvgSalaryAfter) {

            /*System.out.println("******************************************");
            System.out.println(this);
            System.out.println(depTo);*/


            System.out.println(this.getDepartmentId() + ":" + getDepartmentName() + " --- " +
                    employee.getFirstName() + " " + employee.getLastName() + " -->>>" + depTo.getDepartmentId() + ":" + depTo.getDepartmentName());
            System.out.println("before: " + fromDepAvgSalaryBefore + " | " + toDepAvgSalaryBefore);
            System.out.println("after: " + fromDepAvgSalaryAfter + " | " + toDepAvgSalaryAfter);
            salaryIncreasesInBothDepartments = true;

            /*System.out.println(this);
            System.out.println(depTo);
            System.out.println("******************************************");*/
        }

        /*вернуть списки в исходное состояние*/
        /*depTo.getEmployeeList().remove(employee);
        this.employeeList.add(employee);*/

        return salaryIncreasesInBothDepartments;
    }
}
