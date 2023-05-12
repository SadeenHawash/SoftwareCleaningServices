package org.example;
import java.util.List;

import static org.example.Functions.printing;

public class OrderStatistics {
    Customer customer = new Customer();
    List<Order> orders = Functions.getOrdersFromFile("orders.txt", customer.getId());
    int totalDeliveredItems = 10;
    double totalCash = 5000;
    double totalDiscount = 500;
    double totalPaid = 4500;
    double totalDebts = 0.0;
    double paid = 3000;
    public List<Order> getOrders(){
        return orders;
    }
    public OrderStatistics(){
        for (Order order : this.orders) {
            if (order.getStatus() == Order.Status.COMPLETE) {

                double totalPrice = 500;
                double totalDiscountAmount = totalPrice * 10 / 100;
                double totalAmountPaid = totalPrice - totalDiscountAmount;
                double totalDebt = totalAmountPaid - paid;
                totalCash += totalAmountPaid;
                totalDiscount += totalDiscountAmount;
                totalPaid += totalAmountPaid;
                totalDebts += totalDebt;
            }
        }
        printing.printSomething("\n=================statistics=================" + "\n============================================" +
                "\n-->Total Delivered Items: " + totalDeliveredItems + "\n-->Total Cash: $" + totalCash +
                "\n-->Total Discount: $" + totalDiscount + "\n-->Total Paid: $" + totalPaid +
                "\n-->Total Debts: $" + totalDebts + "\n============================================\n\n");
    }



}
