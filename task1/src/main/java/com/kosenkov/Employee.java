package com.kosenkov;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 4754460787727245401L;

    private int id;
    private int departmentId;

    // firstName и lastName не могут быть пустыми
    private String firstName;
    private String lastName;

    private String secondName;
    private int salary;
    private double dSalary;
    private transient String password;

    private BigDecimal xSalary;
    private String fio;

    public Employee(BigDecimal xSalary, String fio) {
        this.xSalary = xSalary;
        this.fio = fio;
    }

    public Employee(String firstName, String lastName, String secondName, BigDecimal xSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.xSalary = xSalary;
        this.salary = xSalary.intValue();
    }

    public Employee(String firstName, String lastName, BigDecimal xSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.xSalary = xSalary;
        this.salary = xSalary.intValue();
    }

    public Employee(String firstName, String lastName, String secondName, int id, int departmentId, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.id = id;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "------------------------------------------------------\n" +
                firstName + " " + lastName + " " + secondName + "\nId: " + id +
                "\nDepartment: " + departmentId + "\nsalary: " + salary +
                "\nPassword: " + password +
                "\n------------------------------------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (salary != employee.salary) return false;
        if (!lastName.equals(employee.lastName)) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (!Objects.equals(secondName, employee.secondName)) return false;
        if (!password.equals(employee.password)) return false;
        return departmentId == employee.departmentId;
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + departmentId;
        result = 31 * result + salary;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
