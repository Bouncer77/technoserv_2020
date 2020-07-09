package com.kosenkov;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class WriteEmployee {
    public static void main( String[] args ) {

        System.out.println( "Начало сериализации!");
        Employee employee1 = new Employee("Ivanov", "Ivan", "Ivanovich",
                42, "United States Department of Education", 100_000);
        employee1.setPassword("123321");

        Employee employee2 = new Employee("Nik", "Ivan", "Ivanovich",
                22, "United States Department of Education", 30_000);

        Employee[] employees = new Employee[2];
        employees[0] = employee1;
        employees[1] = employee2;
        System.out.println(Arrays.toString(employees));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Сериализация завершена!");
    }
}
