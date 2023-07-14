package services;

import models.ParkingLot;
import models.ParkingSpot;
import models.vehicles.Vehicle;
import utils.Category;
import utils.UserRole;

/**
 * This class is used to manage a parking lot
 */
public class ParkingSystem {
    private final ParkingLot parkingLot;

    public ParkingSystem(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * It handles parking spots assigning for a specific vehicle category and its owner role
     *
     * @param vehicle vehicle to be parked
     * @return occupied parking spot if available, otherwise null
     */
    public ParkingSpot park(Vehicle vehicle) {
        ParkingSpot result;
        UserRole userRole = vehicle.getOwner().getRole();

        switch (userRole) {
            case REGULAR:
                return this.parkingLot.occupySpot(vehicle.getCategory());
            case VIP:
                result = this.parkingLot.occupySpot(vehicle.getCategory());
                int categoryOrdinal = vehicle.getCategory().ordinal();

                while ((result == null) && (categoryOrdinal < Category.LARGE.ordinal())) {
                    categoryOrdinal++;
                    result = this.parkingLot.occupySpot(Category.values()[categoryOrdinal]);
                }

                return result;
        }

        return null;
    }
}
