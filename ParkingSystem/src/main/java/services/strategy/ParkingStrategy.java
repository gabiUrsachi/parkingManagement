package services.strategy;


import models.ParkingLot;
import models.ParkingSpot;
import models.vehicles.Vehicle;

/**
 * This class defines the signature for a parking behaviour, but it lets subclasses to implement the steps to be followed in order to achieve this
 */
public abstract class ParkingStrategy {
    protected final ParkingLot parkingLot;

    public ParkingStrategy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * It finds the parking spot that matches vehicle parking requirements
     *
     * @param vehicle the vehicle which needs to be parked
     * @return available parking spots if exists, otherwise null
     */
    public abstract ParkingSpot park(Vehicle vehicle);

    protected void occupySpot(ParkingSpot parkingSpot, Vehicle vehicle) {
        this.parkingLot.addSpotVehicle(parkingSpot, vehicle);
    }

    protected void releaseSpot(ParkingSpot parkingSpot) {
        this.parkingLot.addSpotVehicle(parkingSpot, null);
    }
}
