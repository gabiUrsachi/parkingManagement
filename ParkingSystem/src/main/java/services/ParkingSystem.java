package services;

import models.ParkingLot;
import models.ParkingSpot;
import models.vehicles.Vehicle;
import utils.Category;
import utils.Type;
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
        UserRole userRole = vehicle.getOwner().getRole();

        switch (userRole) {
            case REGULAR:
                return findAnyTypeSpot(vehicle.getCategory(), vehicle.getType(),false);
            case VIP:
                return findAnyTypeSpot(vehicle.getCategory(), vehicle.getType(), true);
        }

        return null;
    }


    private ParkingSpot findAnyTypeSpot(Category categoryLimit, Type typeLimit, Boolean anyCategory) {
        ParkingSpot result = null;
        int typeOrdinal = typeLimit.ordinal();

        // free categories for current typeLimit and then free categories for the other types
        while (result == null && typeOrdinal >= Type.NON_ELECTRICAL.ordinal()) {
            result = this.parkingLot.occupySpot(categoryLimit, Type.values()[typeOrdinal]);

            if(anyCategory && result == null) {
                result = findAnyCategorySpot(categoryLimit, Type.values()[typeOrdinal]);
            }

            typeOrdinal--;
        }

        return result;
    }


    private ParkingSpot findAnyCategorySpot(Category categoryLimit, Type type){
        ParkingSpot result = null;
        int categoryOrdinal = categoryLimit.ordinal();

        while (result == null && categoryOrdinal < Category.LARGE.ordinal()) {
            categoryOrdinal++;
            result = this.parkingLot.occupySpot(Category.values()[categoryOrdinal], type);
        }

        return result;
    }
}

