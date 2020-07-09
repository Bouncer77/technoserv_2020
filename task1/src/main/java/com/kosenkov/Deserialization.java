package com.kosenkov;

import java.io.*;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * lesson 1
 */

public class Deserialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("save.bin");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Employee employee1 = (Employee) objectInputStream.readObject();

        System.out.println(employee1);
    }
}
