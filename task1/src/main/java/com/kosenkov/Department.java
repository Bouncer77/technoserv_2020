package com.kosenkov;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Kosenkov Ivan on 09.07.2020
 * Класс описывающий отдел
 * @see com.kosenkov.Employee
 * */
public class Department {

    private String departmentName;
    private List<Employee> employeeList;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.employeeList = new LinkedList<>();
    }

    public Department(String departmentName, Employee employee) {
        this.departmentName = departmentName;
        this.employeeList = new LinkedList<>();
        employeeList.add(employee);
    }

    String getDepartmentName() {
        return departmentName;
    }

    List<Employee> getEmployeeList() {
        return employeeList;
    }

    /** Рассчитать среднюю зарплату в отделе */
    public BigDecimal getAvgSalary() {
        return this.getAvgSalary(this.getSumSalaryInDepartment(), this.employeeList.size());
    }

    /**
     * Рассчитать среднюю зарплату в отделе
     * @param sumSalary Сумма зарплат по отделу
     * @param num Количество человек в отделе
     * @return Средняя зарплата по отделу
     * */
    public BigDecimal getAvgSalary(BigDecimal sumSalary, long num) {
        return sumSalary.divide(BigDecimal.valueOf(num), 2, RoundingMode.HALF_UP);
    }

    /** Сумма всех зарплат в отделе */
    public BigDecimal getSumSalaryInDepartment() {
        BigDecimal sumSalary = BigDecimal.valueOf(0.0);
        for (Employee em : employeeList) {
            sumSalary = sumSalary.add(em.getSalary());
        }
        return sumSalary;
    }

    BigDecimal getAvgGroupSalary(int[] group) {
        BigDecimal sumGroupSalary = BigDecimal.valueOf(0.0);
        for (int value : group) {
            sumGroupSalary = sumGroupSalary.add(this.getEmployeeList().get(value - 1).getSalary());
        }
        return sumGroupSalary.divide(BigDecimal.valueOf(group.length), 2, RoundingMode.HALF_UP);
    }

    public String transferringEmployeeToAnotherDepartment(int employeeNum, Department depTo) {
        BigDecimal fromDepAvgSalaryBefore = this.getAvgSalary();
        BigDecimal toDepAvgSalaryBefore = depTo.getAvgSalary();
        Employee employee = employeeList.get(employeeNum);

        BigDecimal fromDepAvgSalaryAfter = this.getAvgSalary(
                getSumSalaryInDepartment().subtract(employee.getSalary()), this.employeeList.size() - 1);

        BigDecimal toDepAvgSalaryAfter = depTo.getAvgSalary(
                depTo.getSumSalaryInDepartment().add(employee.getSalary()), depTo.employeeList.size() + 1);

        return "Перевод сотрудника " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getSecondName() +
                "\nИз " + departmentName + " : " + fromDepAvgSalaryBefore + " ---> " + fromDepAvgSalaryAfter +
                "\nВ " + depTo.getDepartmentName() + " : " + toDepAvgSalaryBefore + " ---> " + toDepAvgSalaryAfter + "\n\n";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(departmentName + " | Средняя зарплата по департаменту: " + this.getAvgSalary() + "\n");
        long i = 0;
        for (Employee em : employeeList) {
            stringBuilder.append("    ").append(++i).append(" : ").append(em.getFio()).
                    append(" Зарплата: ").append(em.getSalary()).append("\n");
        }
        return stringBuilder.toString();
    }

    public static boolean validationDepartmentName(String depName) {
        if (depName.matches("[а-яА-Я0-9]+[а-яА-Я 0-9]*") || depName.matches("[a-zA-Z0-9]+[a-zA-Z 0-9]*")) {
            return true;
        } else {
            System.out.println(Colour.ANSI_YELLOW + "Предупреждение!" + Colour.ANSI_RESET + " Отброшен сотрудник - недопустимое имя отдела: " + depName);
            return false;
        }
    }

    public boolean validationGroupForTransfer(int[] group, int k) {
        BigDecimal sumSalary = BigDecimal.valueOf(0.0);
        for (int i = 0; i < k; i++) {
            sumSalary = sumSalary.add(employeeList.get(group[i] - 1).getSalary());
        }
        BigDecimal avgGroupSalary = sumSalary.divide(BigDecimal.valueOf(group.length), 2, RoundingMode.HALF_UP);
        return avgGroupSalary.compareTo(getAvgSalary()) < 0;
    }

    public List<int[]> combinationsWithoutRepetitionsAndAvgGroupSalaryLessThanAvgInDep(int k) {
        List<int[]> groupList = new LinkedList<>(); // Для сохранения порядка вывода от меньших групп к более большим

        int n = employeeList.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        while (nextSet(arr, k)) {
            if (validationGroupForTransfer(arr, k)) {
                groupList.add(Arrays.copyOf(arr, k));
            }
        }

        return groupList;
    }

    public static boolean nextSet(int[] arr, int k) {
        int n = arr.length;
        for (int i = k - 1; i >= 0 ; --i) {
            if (arr[i] < n - k + i + 1) {
                ++arr[i];
                for (int j = i + 1; j < k; ++j) {
                    arr[j] = arr[j - 1] + 1;
                }
                return true;
            }
        }
        return false;
    }
}
