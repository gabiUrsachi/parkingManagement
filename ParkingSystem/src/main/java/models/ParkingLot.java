package models;

import models.vehicles.Vehicle;

import java.util.HashMap;

/**
 * This class is used for maintaining parking spots states
 */
public class ParkingLot {
    private HashMap<ParkingSpot, Vehicle> spotVehicleAssociations;

    public void addSpotVehicle(ParkingSpot parkingSpot, Vehicle vehicle) {
        spotVehicleAssociations.put(parkingSpot, vehicle);
    }

    public HashMap<ParkingSpot, Vehicle> getSpotVehicleAssociations() {
        return spotVehicleAssociations;
    }

    public void setSpotVehicleAssociations(HashMap<ParkingSpot, Vehicle> spotVehicleAssociations) {
        this.spotVehicleAssociations = spotVehicleAssociations;
    }
}
