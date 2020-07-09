package com.kosenkov;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Сериализация экземпляра класса Employee (сотрудник, работник)
 */
public class Serialization
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Начало сериализации!");
        Employee employee1 = new Employee("Ivanov", "Ivan", "Ivanovich",
                42, "United States Department of Education", 100_000);
        FileOutputStream outputStream = new FileOutputStream("D:\\Projects\\technoserv_2020\\task1\\save\\save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(employee1);
        objectOutputStream.close();
        System.out.println( "Сериализация завершена!");
    }
}
