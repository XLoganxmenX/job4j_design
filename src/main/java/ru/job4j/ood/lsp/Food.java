package ru.job4j.ood.lsp;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Food {
    private int id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime expiryDate;
    private float price;
    private double discount;
    private final List<String> components;

    public Food(int id, String name, LocalDateTime createDate, LocalDateTime expiryDate,
                float price, double discount, List<String> components) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
        this.components = components;
    }

    public Food(String name, LocalDateTime createDate, LocalDateTime expiryDate,
                float price, double discount, List<String> components) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
        this.components = components;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComponents() {
        return components;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
