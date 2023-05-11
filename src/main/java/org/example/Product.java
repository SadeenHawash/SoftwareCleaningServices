package org.example;

public class Product {
    private float area;
    private String name;
    private String picture;
    private String material;
    private String treatment;
    private String customerId;
    private int orderId;

    public Product(String name,String material,float area , String treatment, String picture ) {
        this.name=name;
        this.material = material;
        this.area = area;
        this.treatment = treatment;
        this.picture = picture;
    }

    public Product() {

    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    public void setArea(float area) {
        this.area = area;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setOrderId(int orderId) {
        this.orderId= orderId;
    }
    public String getName() {
        return name;
    }
    public float getArea() {
        return area;
    }
    public String getTreatment() {
        return treatment;
    }
    public String getMaterial() {
        return material;
    }
    public String getPicture() {
        return picture;
    }
    public String getCustomerId() {
        return customerId;
    }
    public int getOrderId() {
        return orderId;
    }

}

