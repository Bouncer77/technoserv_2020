package com.kosenkov;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 31.07.2020
 */

public class Store {

    private static int goods = 1000;
    private static int numberTransactions = 0;

    public static String printStdOut() {
        return Colour.red("Store:") +
                Colour.green("\n    goods: " + goods +
                Colour.yellow("\n    numberTransactions: " + numberTransactions));
    }

    public static synchronized int buyProducts(int count) {

        if (goods == 0) {
            return 0;
        }

        numberTransactions++;
        if (count > goods) {
            count = goods;
            goods = 0;
        } else {
            goods -= count;
        }
        return count;
    }

    public static int getGoods() {
        return goods;
    }
}
