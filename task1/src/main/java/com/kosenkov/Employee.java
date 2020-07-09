package com.kosenkov;

import java.io.Serializable;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * lesson 1
 */

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lastName;
    private String firstName;
    private String secondName;
    private int age;
    private String department;
    private int salary;

    public Employee(String lastName, String firstName, String secondName, int age, String department, int salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "------------------------------------------------------\n" +
                lastName + " " + firstName + " " + secondName + "\nAge: " + age +
                "\nDepartment: " + department + "\nsalary: " + salary +
                "\n------------------------------------------------------";
    }
}
