package com.kosenkov;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 31.07.2020
 */

public class Customer implements Runnable {

    private int goods = 0;
    private int numberTransactions = 0;
    private static CyclicBarrier barrier;

    public int getGoods() {
        return goods;
    }

    public int getNumberTransactions() {
        return numberTransactions;
    }

    public static void setCyclicBarrier(int customer) {
        barrier = new CyclicBarrier(customer);
    }

    private void buyGoods(int count) {

        if (count == 0) return;

        goods += count;
        numberTransactions++;
    }

    @Override
    public void run() {

        while (Store.getGoods() > 0) {
            try {
                barrier.await();
                this.buyGoods(Store.buyProducts(Utils.getRandomCount()));
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Пожалуйста, воспользуйтесь другим человеком для запуска этой программы");
            }
        }
    }

    @Override
    public String toString() {
        return Colour.green(" Goods: " + this.getGoods()) + " | " + Colour.yellow("NumberTransactions = " + this.getNumberTransactions());
    }
}
