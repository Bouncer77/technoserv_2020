package com.kosenkov;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * tack 1 part 2
 */

public class Main {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("123.44444444444444444444444444444444444444444444444");
        /*System.out.println(bigDecimal);
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, ROUND_HALF_UP);
        System.out.println(bigDecimal1);*/

        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
        System.out.println();
        ReadFile.readFile(args[0]);
    }
}
