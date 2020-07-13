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

    private String firstName; // имя
    private String lastName; // фамилия
    private String secondName; // отчество
    private BigDecimal salary; // зарплата

    public Employee(String firstName, String lastName, String secondName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "------------------------------------------------------\n" +
                firstName + " " + lastName + " " + secondName + "\nsalary: " + salary +
                "\n------------------------------------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!Objects.equals(firstName, employee.firstName)) return false;
        if (!Objects.equals(lastName, employee.lastName)) return false;
        if (!Objects.equals(secondName, employee.secondName)) return false;
        return Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    public BigDecimal getSalary() {
        return new BigDecimal(salary.toString());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
