package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
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

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeList = new LinkedList<>();
    }

    // new
    public Department(String departmentName, Employee employee) {
        this.departmentName = departmentName;
        this.employeeList = new LinkedList<>();
        employeeList.add(employee);
    }

    // new
    public void addEmployeeToDepartment(Employee employee) {
        this.employeeList.add(employee);
    }

    // new
    public static void addToDepartment(Employee employee, String departmentName, List<Department> departmentList) {
        if (hasDepartment(departmentName, departmentList)) {
            addEmployeeToDepartment(departmentName, departmentList, employee);
        } else {
            departmentList.add(new Department(departmentName, employee));
        }
    }

    // new
    public static boolean hasDepartment(String departmentName, List<Department> departmentList) {
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(departmentName)) {
                return true;
            }
        }
        return false;
    }

    // new
    public static boolean addEmployeeToDepartment(String departmentName, List<Department> departmentList, Employee employee) {
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(departmentName)) {
                dep.getEmployeeList().add(employee);
                return true;
            }
        }
        return false;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    private List<Employee> getEmployeeList() {
        return this.employeeList;
        // return new LinkedList<>(this.employeeList);
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
                      dep1.averageSalaryIncreasesInBothDepartments(i, dep2);
                    }
                }
            }
        }
    }

    private void averageSalaryIncreasesInBothDepartments(int employeeNum, Department depTo) {

        double fromDepAvgSalaryBefore = this.takeAvgSalary();
        double toDepAvgSalaryBefore = depTo.takeAvgSalary();
        Employee employee = employeeList.get(employeeNum);
        double fromDepAvgSalaryAfter = this.takeAvgSalaryWithOutOne(employee.getSalary());
        double toDepAvgSalaryAfter = depTo.takeAvgSalaryOneMore(employee.getSalary());

        /*средняя зарплата увеличивается в обоих отделах*/
        if (fromDepAvgSalaryBefore < fromDepAvgSalaryAfter &&
                toDepAvgSalaryBefore < toDepAvgSalaryAfter) {

            // Вывод в терминал
            System.out.println(this.getDepartmentId() + ":" + getDepartmentName() + " --- " +
                    employee.getFirstName() + " " + employee.getLastName() + " -->>> " +
                    depTo.getDepartmentId() + ":" + depTo.getDepartmentName());
            System.out.println("before: " + fromDepAvgSalaryBefore + " | " + toDepAvgSalaryBefore);
            System.out.println("after: " + fromDepAvgSalaryAfter + " | " + toDepAvgSalaryAfter);

            // Запись в файл
            try (PrintWriter pw = new PrintWriter(new File("averageSalaryIncreasesInBothDepartments.txt"))){
                pw.println(this.getDepartmentId() + ":" + getDepartmentName() + " --- " +
                        employee.getFirstName() + " " + employee.getLastName() + " -->>> " +
                        depTo.getDepartmentId() + ":" + depTo.getDepartmentName());
                pw.println("before: " + fromDepAvgSalaryBefore + " | " + toDepAvgSalaryBefore);
                pw.println("after: " + fromDepAvgSalaryAfter + " | " + toDepAvgSalaryAfter);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Department> createDepartmentListFromEmployeeList(List<Employee> employeeList) {
        List<Department> departmentList = new LinkedList<>();
        for (Employee em : employeeList) {
            boolean departmentFound = false;
            for (Department d : departmentList) {
                if (em.getDepartmentId() == d.getDepartmentId()) {
                    List<Employee> emList = d.getEmployeeList();
                    emList.add(em);
                    departmentFound = true;
                }
            }

            if (!departmentFound) {
                List<Employee> employeeListTmp = new LinkedList<>();
                employeeListTmp.add(em);
                departmentList.add(new Department(em.getDepartmentId(),
                        Department.takeDepartmentNameById(em.getDepartmentId()), employeeListTmp));
            }
        }
        return departmentList;
    }
}
