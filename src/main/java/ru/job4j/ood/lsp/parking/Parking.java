package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {

    Vehicle park(Vehicle vehicle);

    Vehicle replace(int id, Vehicle vehicle);

    Vehicle remove(Vehicle vehicle);

    Vehicle findById(int id);

    List<Vehicle> findAll();
}
