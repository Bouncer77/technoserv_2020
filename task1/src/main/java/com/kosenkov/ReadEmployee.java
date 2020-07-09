package com.kosenkov;

import java.io.*;
import java.util.Arrays;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 09.07.2020
 * task 1
 */
public class ReadEmployee {
    public static void main(String[] args) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))){
            Employee[] employees = (Employee[])ois.readObject();
            System.out.println(Arrays.toString(employees));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
