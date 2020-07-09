package com.kosenkov;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class WriteEmployee {
    public static void main( String[] args ) {

        System.out.println( "Начало сериализации!");

        Employee employee1 = new Employee("Ivan", "Ivanov", "Ivanovich",
                1, 1, 200_000);
        employee1.setPassword("123321");

        Employee employee2 = new Employee("Nik", "Ivanov", "Ivanovich",
                2, 1, 270_000);

        Employee employee3 = new Employee("Anton", "Ivanov", "Ivanovich",
                3, 2, 40_000);

        Employee employee4 = new Employee("Marina", "Ivanova", "Ivanovich",
                4, 2, 45_000);

        List<Employee> employeeList = List.of(employee1, employee2, employee3, employee4);
        employeeList.forEach(System.out::println);

        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            oos.writeObject(employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Сериализация завершена!");
    }
}
