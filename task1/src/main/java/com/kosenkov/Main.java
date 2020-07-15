package com.kosenkov;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * tack 1 part 2
 */

public class Main {

    public static final int INPUT_FILE_ARGS_INDEX = 0;
    public static final int OUTPUT_FILE_ARGS_INDEX = 1;

    public static void main(String[] args) {
        ReadFile.validationInputArguments(args);
        MyPrint.printArgs(args);
        Company company = ReadFile.readFile(args[INPUT_FILE_ARGS_INDEX]);
        System.out.println(company);
        MyPrint.printWhenAvgSalaryIncreases(company, args[OUTPUT_FILE_ARGS_INDEX]);
    }
}
