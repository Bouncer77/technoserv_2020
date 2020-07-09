package com.kosenkov;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class Serialization {
    public static void main( String[] args ) {

        System.out.println( "Начало сериализации!");
        Employee employee1 = new Employee("Ivanov", "Ivan", "Ivanovich",
                42, "United States Department of Education", 100_000);

        Employee employee2 = new Employee("Nik", "Ivan", "Ivanovich",
                22, "United States Department of Education", 30_000);

        try (FileOutputStream fos = new FileOutputStream("save.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(employee1);
            oos.writeObject(employee2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Сериализация завершена!");
    }
}
