package com.kosenkov;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * tack 1 part 2
 */

public class Main {
    public static void main(String[] args) {
        ReadFile.validationInputArguments(args);
        MyPrint.printArgs(args);
        Company company = ReadFile.readFile(args[0]);
        MyPrint.averageSalaryPerDepartments(company);
        MyPrint.printWhenAvgSalaryIncreases(company, args[1]);
    }
}
