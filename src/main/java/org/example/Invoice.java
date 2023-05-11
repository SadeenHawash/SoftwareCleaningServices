package org.example;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private final double totalPrice ;
    private final String name;
    private final String address ;
    private List<Product> products = new ArrayList<>();
    public Invoice(Customer customer) {
        this.totalPrice = customer.totalInvoicePrice(customer.orders);
        this.name = customer.getName();
        this.address = customer.getAddress();
        for (Order order: customer.getOrders()){
            this.products = order.getProducts();
        }
    }
    public void invoiceRes(Customer customer){
        ArrayList<Order> tmp = (ArrayList<Order>) Functions.getOrdersFromFile("orders.txt", customer.getId());
        System.out.println(" "+ customer.getName()+"  "+ customer.getAddress()+"  "+"Total Price: "+ customer.totalInvoicePrice(tmp));
        double dis = (customer.totalInvoicePrice(tmp)*10)/100;
        System.out.println(" The Discount: "+ dis);
        for (Order order:tmp){
            System.out.println("\n\t Id: "+ order.getOrderId() +"  Price: "+order.getTotalPrice());
            ArrayList<Product> tmp1 = (ArrayList<Product>) Functions.getProductsFromFile("products.txt", customer.getId(), String.valueOf(order.getOrderId()));
            for (Product product:tmp1){
                System.out.println("\t\t"+product.getName()+"  "+ product.getMaterial()+"  "+ product.getArea()+"  "+product.getTreatment());
            }
        }
    }
    public double getTotalPrice(){
        return this.totalPrice;
    }
    public String getCustomerName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }
    public List<Product> getProducts() {
        return this.products;
    }
}
