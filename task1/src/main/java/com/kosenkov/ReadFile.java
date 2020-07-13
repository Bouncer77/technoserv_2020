package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * lesson
 */

public class ReadFile {
    public static void readFile(String fileName) {
        List<Department> departmentList = new LinkedList<>();
        try(final Scanner scanner = new Scanner(new File("input.txt"))){
            while (scanner.hasNextLine()) {
                // System.out.println(scanner.nextLine());
                String fio_line = scanner.nextLine();
                String[] fio = fio_line.split(" ");
                System.out.println(fio_line);
                System.out.println(Arrays.toString(fio));


                String salary = scanner.nextLine();
                System.out.println(salary);
                BigDecimal bigDecimal = new BigDecimal(Double.parseDouble(salary)).setScale(2, ROUND_HALF_UP);
                System.out.println("BigDecimal: " + bigDecimal);

                String departmentName = scanner.nextLine();
                System.out.println(departmentName);

                Department.addToDepartment(new Employee(fio[0], fio[1], bigDecimal), departmentName, departmentList);
                //scanner.nextLine();
            }
            System.out.println(departmentList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл с именем " + fileName + " не найден!");
        }
    }
}
