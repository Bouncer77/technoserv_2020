package com.kosenkov;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Kosenkov Ivan on 09.07.2020
 * Класс описывающий сотрудника
 */
public class Employee {

    private String lastName; // фамилия
    private String firstName; // имя
    private String secondName; // отчество
    private BigDecimal salary; // зарплата

    public Employee(String lastName, String firstName, String secondName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.salary = salary;
    }

    BigDecimal getSalary() {
        return salary;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getSecondName() {
        return secondName;
    }

    String getFio() {
        return lastName + " " + firstName + " " + secondName;
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

        if (!lastName.equals(employee.lastName)) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (secondName != null ? !secondName.equals(employee.secondName) : employee.secondName != null) return false;
        return salary.equals(employee.salary);
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + salary.hashCode();
        return result;
    }

    public static boolean validationSalary(String salaryLine) {

        if (salaryLine.matches("\\d+\\.\\d{2}") || salaryLine.matches("\\d+")) {
            return true;
        } else {
            System.out.println(MyPrint.printWarning() + " Отброшен сотрудник - неверно указан формат зарплаты: " + salaryLine);
            return false;
        }
    }

    public static boolean validationFio(String[] fio) {

        if (fio.length > 3 || fio.length < 2) {
            System.out.println(Colour.ANSI_YELLOW + "Предупреждение!" + Colour.ANSI_RESET + " Указано неверное количество аргументов для проверки ФИО." +
                    " Требуемое количество аргументов от 2-х до 3-х. Переданое - " + fio.length + " : " + Arrays.toString(fio));
            return false;
        }

        if (!fio[0].matches("[а-яёА-ЯЁ]+[\\-?[а-яёА-ЯЁ]*]*") || fio[0].matches("[a-zA-Z]+[\\-?[a-zA-Z]*]*")) {
            System.out.println(Colour.ANSI_YELLOW + "Предупреждение!" + Colour.ANSI_RESET + " Ошибка в имени. Имя сотрудника: " + fio[0]);
            return false;
        }

        if (!fio[1].matches("[а-яёА-ЯЁ]+[\\-?[а-яёА-ЯЁ]*]*") || fio[1].matches("[a-zA-Z]+[\\-?[a-zA-Z]*]*")) {
            System.out.println(Colour.ANSI_YELLOW + "Предупреждение!" + Colour.ANSI_RESET + " Ошибка в фамилии. Фамилия сотрудника: " + fio[1]);
            return false;
        }

        if (fio.length == 3 && !fio[2].isEmpty()) {
            if (!fio[2].matches("[а-яёА-ЯЁ]+[\\-?[а-яёА-ЯЁ]*]*") || fio[2].matches("[a-zA-Z]+[\\-?[a-zA-Z]*]*")) {
                System.out.println(Colour.ANSI_YELLOW + "Предупреждение!" + Colour.ANSI_RESET + " Ошибка в отчестве. Отчество сотрудника: " + fio[2]);
                return false;
            }
        }

        return true;
    }
}
