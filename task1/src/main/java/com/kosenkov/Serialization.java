package com.kosenkov;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Сериализация экземпляра класса Employee (сотрудник, работник)
 */
public class Serialization {
    public static void main( String[] args ) {

        System.out.println( "Начало сериализации!");
        Employee employee1 = new Employee("Ivanov", "Ivan", "Ivanovich",
                42, "United States Department of Education", 100_000);

        try {
            FileOutputStream fos = new FileOutputStream("save.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employee1);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Сериализация завершена!");
    }
}
