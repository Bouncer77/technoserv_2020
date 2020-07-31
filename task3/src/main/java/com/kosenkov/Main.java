package com.kosenkov;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main( String[] args ) {

        if (!Utils.validatioInputValues(args)) {
            System.out.println(Colour.red("Ошибка: аргумент должен содержать число покупателей"));
            System.exit(1);
        }

        int customers = Integer.parseInt(args[0]);
        Customer.setCyclicBarrier(customers);
        List<Customer> customerList = new ArrayList<>(customers);

        for (int i = 0; i < customers; i++) {
            Customer customer = new Customer();
            customerList.add(customer);
            customer.start();
        }

        customerList.forEach(c -> {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        int sumProducts = 0;
        int sumOperations = 0;
        for (Customer customer : customerList){
            sumProducts += customer.getGoods();
            sumOperations += customer.getNumberTransactions();
            System.out.println(Colour.purple(customer.getName()) + customer);
        }
        System.out.println("all buyed goods = " + sumProducts + " | AllNumberTransactions = " + sumOperations);
        System.out.println(Store.printStdOut());
    }
}
