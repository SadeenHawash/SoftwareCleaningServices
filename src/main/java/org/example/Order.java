package org.example;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int orderId;
    private double amountPaid;
    private double cashCollected;
    private Status status;
    private List<Product> products = new ArrayList<>();
    private String customerId;
    Worker assignedWorker = new Worker();
    private double totalPrice;

    public Order(int orderId, String orderCustomerId, int workerId, String workerName, Status status, double price) {
        this.orderId = orderId;
        this.customerId = orderCustomerId;
        this.assignedWorker.setId(String.valueOf(workerId));
        this.assignedWorker.setName(workerName);
        this.status = status;
        this.totalPrice = price;
    }

    public static Order getOrderFromLine(String line) {
        Worker worker = new Worker();
        String[] items = line.split(" , ");
        Order order = new Order(Integer.parseInt(items[1]));
        order.setCustomerId(items[0]);
        worker.setId(items[2]);
        worker.setName(items[3]);
        order.setStatus(Status.valueOf(items[4]));
        order.setTotalPrice(Double.parseDouble(items[5]));
        order.assignedWorker(worker);
        return  order;
    }
    public enum Status {
        WAITING,
        IN_TREATMENT,
        COMPLETE
    }
    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
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

