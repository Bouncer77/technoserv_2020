package com.kosenkov;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * task 1 part 2
 */

public class CompanySalaryCalculatorException extends RuntimeException {
    public CompanySalaryCalculatorException(String message) {
        super(message);
    }

    public CompanySalaryCalculatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
