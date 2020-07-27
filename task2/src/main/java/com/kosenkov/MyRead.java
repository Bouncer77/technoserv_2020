package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyRead {
    public static ArrayList<Row> readTable(String fileName) {
        ArrayList<Row> rowList = new ArrayList<>();
        try (final Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] inputFields = scanner.nextLine().split(",");
                for (int i = 0; i < inputFields.length; i++)
                    inputFields[i] = inputFields[i].trim();

                if (Row.validationRow(inputFields)) {
                    rowList.add(new Row(Integer.parseInt(inputFields[Row.ID]), inputFields[Row.VALUE]));
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден!");
        }

        return rowList;
    }
}
