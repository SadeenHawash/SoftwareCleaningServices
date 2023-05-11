package org.example;

import java.util.logging.Logger;

class Discount {
    private final String customerName;
    private final double totalSpending;
    private final int discountRate;
    Logger logger = Logger.getLogger(" ");
    public Discount(String customerName, double totalSpending, int discountRate) {
        this.customerName = customerName;
        this.totalSpending = totalSpending;
        this.discountRate = discountRate;
    }

    public void printDetails() {
        String tmp;
        tmp = "Customer Name: " + this.customerName;
        logger.info(tmp);
        tmp ="Total Spending: " + this.totalSpending;
        logger.info(tmp);
        tmp ="Discount Rate: " + this.discountRate + "%";
        logger.info(tmp);
    }
}


