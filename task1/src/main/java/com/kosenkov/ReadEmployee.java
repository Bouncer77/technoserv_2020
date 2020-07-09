package com.kosenkov;

import java.io.*;
import java.util.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class ReadEmployee {

    public static void main(String[] args) {

        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))){
            @SuppressWarnings("unchecked")
            List<Employee> employeeList = (List<Employee>) ois.readObject();
            // employeeList.forEach(System.out::println);

            Map<String, Double> avgByDep = averageSalaryByDepartments(employeeList);
            System.out.println(avgByDep);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Map<String, Double> averageSalaryByDepartments(List<Employee> employeeList) {

        employeeList.forEach(System.out::println);
        Map<String, Double> map = new HashMap<>();

        //map.put(employeeList.get(0).getDepartment(), (double) employeeList.get(0).getSalary());

        //System.out.println(map.get("United States Department of Education"));
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

        System.out.println("Dep List: ");
        System.out.println(departmentList);

        System.out.println(departmentList.get(0).takeAvgSalary());

        return map;
    }
}
