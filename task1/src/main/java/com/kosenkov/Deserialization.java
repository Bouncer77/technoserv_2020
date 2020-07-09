package com.kosenkov;

import java.io.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class Deserialization {
    public static void main(String[] args) {

        try (FileInputStream fileInputStream = new FileInputStream("save.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Employee employee1 = (Employee) objectInputStream.readObject();
            Employee employee2 = (Employee) objectInputStream.readObject();
            System.out.println(employee1);
            System.out.println(employee2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
