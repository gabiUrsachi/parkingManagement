package services.strategy;

import models.ParkingLot;
import models.ParkingSpot;
import models.vehicles.Vehicle;
import services.ParkingSpotsProcessor;

import java.util.List;

/**
 * This class defines the steps to be followed in order to obtain a parking spot for a regular user
 */
public class RegularParkingStrategy extends ParkingStrategy {

    public RegularParkingStrategy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    /**
     * A vehicle can be parked only on a spot with the same size as vehicle category
     *
     * @param vehicle the vehicle which needs to be parked
     * @return available parking spots if exists, otherwise null
     */
    @Override
    public ParkingSpot park(Vehicle vehicle) {
        ParkingSpot resultedSpot;
        List<ParkingSpot> freeParkingSpots = ParkingSpotsProcessor.findFreeParkingSpots(this.parkingLot);

        if (ParkingSpotsProcessor.foundFreeSpots(freeParkingSpots)) {
            List<ParkingSpot> freeParkingSpotsByCategory = ParkingSpotsProcessor.findFreeParkingSpotsByCategory(freeParkingSpots, vehicle.getCategory());

            if (ParkingSpotsProcessor.foundFreeSpots(freeParkingSpotsByCategory)) {
                List<ParkingSpot> freeParkingSpotsByType = ParkingSpotsProcessor.findFreeParkingSpotsByType(freeParkingSpotsByCategory, vehicle.getType());

                if (ParkingSpotsProcessor.foundFreeSpots(freeParkingSpotsByType)) {
                    resultedSpot = ParkingSpotsProcessor.getFirstParkingSpot(freeParkingSpotsByType);
                } else {
                    resultedSpot = ParkingSpotsProcessor.getFirstParkingSpot(freeParkingSpotsByCategory);
                }

                super.occupySpot(resultedSpot, vehicle);
                return resultedSpot;
            }
        }

        return null;
    }

}
