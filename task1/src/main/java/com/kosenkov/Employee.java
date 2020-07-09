package com.kosenkov;

import java.io.Serializable;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 2370199067824826344L;

    // firstName и lastName не могут быть пустыми
    private String lastName;
    private String firstName;

    private String secondName;
    private int age;
    private String department;
    private int salary;
    private transient String password;

    public Employee(String lastName, String firstName, String secondName, int age, String department, int salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "------------------------------------------------------\n" +
                lastName + " " + firstName + " " + secondName + "\nAge: " + age +
                "\nDepartment: " + department + "\nsalary: " + salary +
                "\nPassword: " + password +
                "\n------------------------------------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (age != employee.age) return false;
        if (salary != employee.salary) return false;
        if (!lastName.equals(employee.lastName)) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (secondName != null ? !secondName.equals(employee.secondName) : employee.secondName != null) return false;
        if (!password.equals(employee.password)) return false;
        return department != null ? department.equals(employee.department) : employee.department == null;
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
