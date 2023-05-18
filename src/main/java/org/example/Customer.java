package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String phone ;
    private String address;
    private String password ;
    private String email;
    List<Order> orders = new ArrayList<>();
    int numberOfOrders =0 ;
    void setNumberOfOrders(int num){
        if(orders.isEmpty()) num = 0;
        this.numberOfOrders = num;
    }
    int getNumberOfOrders(){
        return numberOfOrders;
    }
    public Customer(String id,String name,String phone,String address,String email) {
        this.id= id;
        this.name=name;
        this.phone= phone;
        this.email=email;
        this.address=address;
    }
    public Customer() {

    }
    public Customer(String name, String email) {
        this.name=name;
        this.email=email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }
    public List<Order> getOrders() {
        return this.orders;
    }
    public static Customer getCustomerFromLine(String line){
        Customer customer1 = new Customer();
        String[] items = line.split(" , ");
        customer1.setId(items[0]);
        customer1.setName(items[1]);
        customer1.setPhone(items[2]);
        customer1.setAddress(items[3]);
        customer1.setEmail(items[4]);
        customer1.setPassword(items[5]);
        customer1.setNumberOfOrders(Integer.parseInt(items[6]));
        return  customer1;
    }
    public double totalInvoicePrice(List<Order> custOrders){
        double tmp = 0;
        for (Order order:custOrders){
            tmp+= order.getTotalPrice();
        }
        return tmp;
    }

}
