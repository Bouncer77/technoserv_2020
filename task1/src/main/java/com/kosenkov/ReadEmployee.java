package com.kosenkov;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class ReadEmployee {
    public static void main(String[] args) {

        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))){
            @SuppressWarnings("unchecked")
            List<Employee> employeeList = (List<Employee>) ois.readObject();
            employeeList.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
