package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Invoice {
    Logger logger = Logger.getLogger(" ");
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
        String tt;
        ArrayList<Order> tmp = (ArrayList<Order>) Functions.getOrdersFromFile("orders.txt", customer.getId());
        tt ="\n "+ customer.getName()+"  "+ customer.getAddress()+"  "+"Total Price: "+ customer.totalInvoicePrice(tmp)+"\n";
        logger.info(tt);
        double dis = (customer.totalInvoicePrice(tmp)*10)/100;
        tt = " The Discount: "+ dis+"\n";
        System.out.println(tt);
        for (Order order:tmp){
            tt ="\n\t Id: "+ order.getOrderId() +"  Price: "+order.getTotalPrice()+"\n";
            logger.info(tt);
            ArrayList<Product> tmp1 = (ArrayList<Product>) Functions.getProductsFromFile("products.txt", customer.getId(), String.valueOf(order.getOrderId()));
            for (Product product:tmp1){
                tt ="\t\t"+product.getName()+"  "+ product.getMaterial()+"  "+ product.getArea()+"  "+product.getTreatment()+"\n";
                logger.info(tt);
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
