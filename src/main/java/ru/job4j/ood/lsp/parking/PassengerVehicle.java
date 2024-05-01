package ru.job4j.ood.lsp.parking;

public class PassengerVehicle extends AbstractVehicle {
    public PassengerVehicle(int id, String model) {
        super(id, model);
        setSize(1);
    }

    public PassengerVehicle(String model) {
        super(model);
        setSize(1);
    }
}
