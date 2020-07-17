package com.kosenkov;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 15.07.2020
 * lesson
 */

public class Combination {

    public static long factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    public static long funC_n_k(int n, int k) {

        /*if (n < 0 || k < 0) {
            System.out.println(MyPrint.printWarning("n < 0 или k < 0"));
            return 0;
        }

        if (n < k) {
            System.out.println(MyPrint.printWarning("n < k"));
            return 0;
        }*/
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public static void main(String[] args) {
        int n = 6, k = 4;
        System.out.printf("n = %d, k = %d, C_n_k = %d\n", n, k, funC_n_k(n, k));

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
            //arr[i] = i;
        }

        int num = 1;
        System.out.print(num++ + " : ");
        printComb(arr, k);

        if (n > k) {
            while (nextSet(arr, n, k)) {
                System.out.print(num++ + " : ");
                printComb(arr, k);
            }
        }
    }

    public static void printComb(int[] arr, int k) {
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean nextSet(int[] arr, int n, int k) {
        for (int i = k - 1; i >= 0 ; --i) {
            if (arr[i] < n - k + i + 1) {
                ++arr[i];
                for (int j = i + 1; j < k; ++j) {
                    arr[j] = arr[j - 1] + 1;
                }
                    return true;
            }
        }
        return false;
    }



    public static List<Employee> combination(int index, int k, List<Employee> arr) {
        List<Employee> res = new ArrayList<>();

        for (int i = 1; i <= k; i++) {

        }

        return res;
    }


}
