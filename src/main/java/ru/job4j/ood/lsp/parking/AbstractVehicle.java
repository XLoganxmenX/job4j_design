package ru.job4j.ood.lsp.parking;

public abstract class AbstractVehicle implements Vehicle {
    private int id;
    private String model;
    private int size;

    public AbstractVehicle(int id, String model, int size) {
        this.id = id;
        this.model = model;
        this.size = size;
    }

    public AbstractVehicle(String model, int size) {
        this.model = model;
        this.size = size;
    }

    public AbstractVehicle(String model) {
        this.model = model;
    }

    public AbstractVehicle(int id, String model) {
        this.id = id;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
