package org.example;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int itemsDelivered;
    private double amountPaid;
    private double cashCollected;
    private Status status;
    private List<Product> products = new ArrayList<>();
    private String customerId;
    Worker assignedWorker = new Worker();
    private double totalPrice;

    public static Order getOrderFromLine(String line) {
        Order order = new Order();
        Worker worker = new Worker();
        String[] items = line.split(" , ");
        order.setCustomerId(items[0]);
        order.setOrderId(items[1]);
        worker.setId(items[2]);
        worker.setName(items[3]);
        order.setStatus(Status.valueOf(items[4]));
        order.setTotalPrice(Double.parseDouble(items[5]));
        order.assignedWorker(worker);
        return  order;
    }

    private void setOrderId(String orderId) {
        this.orderId= Integer.parseInt(orderId);
    }

    public enum Status {
        WAITING,
        IN_TREATMENT,
        COMPLETE
    }
    public Order(){

    }
    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
    }
    public Order(int id, int itemsDelivered, double amountPaid, double cashCollected, String status) {
        this.orderId = id;
        this.itemsDelivered = itemsDelivered;
        this.amountPaid = amountPaid;
        this.cashCollected = cashCollected;
        this.status = Status.valueOf(status);
    }
    public Order(int ordId, String workerName, String workerId, double total, String status){
        Worker worker = new Worker(Integer.parseInt(workerId),workerName);
        this.orderId= ordId;
        this.assignedWorker = worker;
        this.totalPrice = total;
        this.status = Status.valueOf(status);

    }
    public int getOrderId() {
        return orderId;
    }

    public int getItemsDelivered() {
        return itemsDelivered;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getCashCollected() {
        return cashCollected;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void setStatus(Status status){
        this.status=status;
    }
    public Status getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice= totalPrice;
    }
    public List<Product> getProducts() {
        return products;
    }


    public void assignedWorker(Worker assignedWorker) {
        this.assignedWorker = assignedWorker;
        status = Status.IN_TREATMENT;
    }

    @Override
    public String toString() {
        return customerId+" , "+orderId+" , "+assignedWorker.getId()+" , "+assignedWorker.getName()+" , "+status+" , "+totalPrice;
    }
}

