package com.example.dinner_app;

public class Offer {
    private String title, dinnerType, deliver, payment;
    private double price;
    public Offer(String title, String dinnerType, String deliver, double price, String payment) {
        this.title = title;
        this.dinnerType = dinnerType;
        this.deliver = deliver;
        this.price = price;
        this.payment = payment;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDinnerType() {
        return dinnerType;
    }
    public void setDinnerType(String dinnerType) {
        this.dinnerType = dinnerType;
    }

    public String getDeliver() {
        return deliver;
    }
    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
}
