package org.example;
import java.util.List;
import java.util.logging.Logger;

public class OrderStatistics {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(" ");
        Customer customer = new Customer();
        List<Order> orders = Functions.getOrdersFromFile("orders.txt", customer.getId());
        int totalDeliveredItems = 10;
        double totalCash = 5000;
        double totalDiscount = 500;
        double totalPaid = 4500;
        double totalDebts = 0.0;

        for (Order order : orders) {
            if (order.getStatus() == Order.Status.COMPLETE) {

                double totalPrice = 500;
                double totalDiscountAmount = totalPrice * 10 / 100;
                double totalAmountPaid = totalPrice - totalDiscountAmount;
                double totalDebt = 200;
                totalCash += totalAmountPaid;
                totalDiscount += totalDiscountAmount;
                totalPaid += totalAmountPaid;
                totalDebts += totalDebt;
            }
        }
        logger.info("\n=================statistics=================" + "\n============================================" +
                "\n-->Total Delivered Items: " + totalDeliveredItems + "\n-->Total Cash: $" + totalCash +
                "\n-->Total Discount: $" + totalDiscount + "\n-->Total Paid: $" + totalPaid +
                "\n-->Total Debts: $" + totalDebts + "\n============================================\n\n");

    }
}
