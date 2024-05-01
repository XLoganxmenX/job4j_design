package ru.job4j.ood.lsp.parking;

public class Truck extends AbstractVehicle {
    public Truck(int id, String model, int size) {
        super(id, model, size);
    }

    public Truck(String model, int size) {
        super(model, size);
    }
}
