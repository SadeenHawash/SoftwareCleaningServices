package org.example;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private String id;
    private String name;
    private String phone;
    private String address;
    private List<Order> assignedOrders;
    private String status;
    private String password;

    public Worker(int id, String name) {
        this.id = String.valueOf(id);
        this.name = name;
        status = "Available";
        assignedOrders = new ArrayList<>();
    }
    public Worker() {

    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name= name;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public void setOrders(List<Order> orders) {
        this.assignedOrders = orders;
    }
    public void updateStatus() {
        if (assignedOrders.isEmpty()) {
            status = "Available";
        } else {
            status = "Busy";
        }
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return assignedOrders;
    }

    @Override
    public String toString() {
        return id+" , "+ name+" , "+phone+" , "+status+" , "+address;
    }
    public String getPassword() {
        return password;
    }
}

