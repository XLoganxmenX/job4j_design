package ru.job4j.ood.lsp.parking;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParkingImpl implements Parking {
    private static final int SMALL_VEHICLE_SIZE = 1;
    private final Map<Integer, Vehicle> smallVehiclePlaces;
    private final Map<Integer, Vehicle> bigVehiclePlaces;
    private final Map<Integer, VehiclePlace> placeMap;
    private int returnId = 1;

    private int remainSmallPlaces;
    private int remainBigPlaces;

    public ParkingImpl(int smallVehiclePlacesSize, int bigVehiclePlacesSize) {
        smallVehiclePlaces = new HashMap<>(smallVehiclePlacesSize, 1.0F);
        remainSmallPlaces = smallVehiclePlacesSize;

        bigVehiclePlaces = new HashMap<>(bigVehiclePlacesSize, 1.0F);
        remainBigPlaces = bigVehiclePlacesSize;

        placeMap = new HashMap<>();
    }

    public int getRemainSmallPlaces() {
        return remainSmallPlaces;
    }

    public int getRemainBigPlaces() {
        return remainBigPlaces;
    }

    @Override
    public Vehicle park(Vehicle vehicle) {
        checkRemainSpace(vehicle);


        List<Map<Integer, Vehicle>> appropriateParks = selectAppropriatePark(vehicle);
        addAndAssignId(appropriateParks, vehicle);
        return vehicle;
    }

    private void checkRemainSpace(Vehicle vehicle) {
        int vehicleSize = vehicle.getSize();

        if (ifSmallVehicle(vehicle) && (remainSmallPlaces < vehicleSize)) {
            throw new IllegalArgumentException("Недостаточно места");
        }

        if (!ifSmallVehicle(vehicle) && (remainSmallPlaces + (remainBigPlaces - vehicleSize) < 0)) {
            throw new IllegalArgumentException("Недостаточно места");
        }
    }

    @Override
    public Vehicle replace(int id, Vehicle vehicle) {
        Vehicle oldVehicle = findById(id);

        if (oldVehicle.getSize() != vehicle.getSize()) {
            throw new IllegalArgumentException("Невозможно заменить автомобили с разным размером");
        }

        VehiclePlace vehiclePlace = placeMap.get(id);
        vehiclePlace.replace(id, vehicle);

        return oldVehicle;
    }

    @Override
    public Vehicle remove(Vehicle vehicle) {
        VehiclePlace removedVehiclePlace = placeMap.remove(vehicle.getId());
        Vehicle removedVehicle = removedVehiclePlace.vehicle;
        removedVehiclePlace.removeFromParks(removedVehicle);

        return removedVehicle;
    }

    @Override
    public Vehicle findById(int id) {
        return placeMap.get(id).vehicle;
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> allVehicles = new LinkedList<>();
        for (VehiclePlace place : placeMap.values()) {
            allVehicles.add(place.vehicle);
        }

        return allVehicles;
    }

    private boolean ifSmallVehicle(Vehicle vehicle) {
        return vehicle.getSize() == SMALL_VEHICLE_SIZE;
    }

    private void addAndAssignId(List<Map<Integer, Vehicle>> parks, Vehicle vehicle) {
        vehicle.setId(returnId++);
        addToPlace(parks, vehicle);
    }

    private void addToPlace(List<Map<Integer, Vehicle>> parks, Vehicle vehicle) {
        VehiclePlace vehiclePlace = new VehiclePlace(vehicle.getId(), parks, vehicle);
        placeMap.put(vehicle.getId(), vehiclePlace);
    }

    private  List<Map<Integer, Vehicle>> selectAppropriatePark(Vehicle vehicle) {
        List<Map<Integer, Vehicle>> parks = new LinkedList<>();
        int abstractVehicleSize = vehicle.getSize();

        if (ifSmallVehicle(vehicle)) {
            parks.add(smallVehiclePlaces);
            int remainBefore = remainSmallPlaces;
            remainSmallPlaces = decreaseRemainingPlaces(remainSmallPlaces, abstractVehicleSize);
            abstractVehicleSize = calculateRemainVehicleSize(remainBefore, remainSmallPlaces, abstractVehicleSize);
        }

        if (!ifSmallVehicle(vehicle)) {
            parks.add(bigVehiclePlaces);
            int remainBefore = remainBigPlaces;
            remainBigPlaces = decreaseRemainingPlaces(remainBigPlaces, abstractVehicleSize);
            abstractVehicleSize = calculateRemainVehicleSize(remainBefore, remainBigPlaces, abstractVehicleSize);
            if (needToUseSmallPlaces(vehicle)) {
                parks.add(smallVehiclePlaces);
                remainSmallPlaces = decreaseRemainingPlaces(remainSmallPlaces, abstractVehicleSize);
                abstractVehicleSize = calculateRemainVehicleSize(remainBefore, remainSmallPlaces, abstractVehicleSize);
            }
        }

        return parks;
    }

    private boolean needToUseSmallPlaces(Vehicle vehicle) {
        return remainBigPlaces < vehicle.getSize()
                && remainSmallPlaces >= vehicle.getSize() - remainBigPlaces;
    }

    private int decreaseRemainingPlaces(int remainingPlaces, int vehicleSize) {
        remainingPlaces -= vehicleSize;
        remainingPlaces = ensureNonNegative(remainingPlaces);
        return remainingPlaces;
    }

    public int ensureNonNegative(int num) {
        return Math.max(num, 0);
    }

    private int calculateRemainVehicleSize(int remainBefore, int remainingPlaces, int vehicleSize) {
        return vehicleSize - (remainBefore - remainingPlaces);
    }

    private class VehiclePlace {
        private int vehicleId;
        private final List<Map<Integer, Vehicle>> parks;
        private Vehicle vehicle;

        public VehiclePlace(int vehicleId, List<Map<Integer, Vehicle>> parks, Vehicle vehicle) {
            this.vehicleId = vehicleId;
            this.parks = parks;
            this.vehicle = vehicle;
            parkVehicle(vehicle);
        }

        public void parkVehicle(Vehicle vehicle) {
            parks.forEach(park -> park.put(vehicleId, vehicle));
        }

        public void removeFromParks(Vehicle vehicle) {
            parks.forEach(park -> park.remove(vehicle.getId()));
        }

        public void replace(int id, Vehicle newVehicle) {
            newVehicle.setId(id);
            parkVehicle(newVehicle);
            vehicle = newVehicle;
            vehicleId = id;
        }
    }

}
