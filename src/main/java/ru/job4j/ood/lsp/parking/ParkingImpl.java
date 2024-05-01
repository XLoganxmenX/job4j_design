package ru.job4j.ood.lsp.parking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingImpl implements Parking {
    private Map<Integer, Vehicle> smallVehiclePlaces;
    private Map<Integer, Vehicle> bigVehiclePlaces;
    private int returnId = 1;

    private int limitSmallPlaces;
    private int limitBigPlaces;
    private int remainSmallPlaces;
    private int remainBigPlaces;

    public ParkingImpl(int smallVehiclePlacesSize, int bigVehiclePlacesSize) {
        smallVehiclePlaces = new HashMap<>(smallVehiclePlacesSize, 1.0F);
        limitSmallPlaces = smallVehiclePlacesSize;
        remainSmallPlaces = smallVehiclePlacesSize;

        bigVehiclePlaces = new HashMap<>(bigVehiclePlacesSize, 1.0F);
        limitBigPlaces = bigVehiclePlacesSize;
        remainBigPlaces = bigVehiclePlacesSize;
    }

    public int getRemainSmallPlaces() {
        return remainSmallPlaces;
    }

    public int getRemainBigPlaces() {
        return remainBigPlaces;
    }

    @Override
    public Vehicle park(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle replace(int id, Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle remove(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle findById(int id) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return List.of();
    }

    private void checkSize(Vehicle vehicle) {

    }
}
