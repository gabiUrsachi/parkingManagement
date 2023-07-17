package services.strategy;

import models.ParkingLot;
import models.ParkingSpot;
import models.vehicles.Vehicle;
import services.ParkingSpotsProcessor;
import utils.Category;

import java.util.List;

/**
 * This class defines the steps to be followed in order to obtain a parking spot for a VIP user
 */
public class VIPParkingStrategy extends ParkingStrategy {

    public VIPParkingStrategy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    /**
     * A vehicle can be parked on a bigger spot also if there are no available spots for that vehicle category
     *
     * @param vehicle the vehicle which needs to be parked
     * @return available parking spots if exists, otherwise null
     */
    @Override
    public ParkingSpot park(Vehicle vehicle) {
        ParkingSpot resultedSpot;
        List<ParkingSpot> freeParkingSpots = ParkingSpotsProcessor.findFreeParkingSpots(this.parkingLot);

        if (ParkingSpotsProcessor.foundFreeSpots(freeParkingSpots)) {
            for (Category category : Category.values()) {
                if (category.ordinal() < vehicle.getCategory().ordinal()) {
                    continue;
                }

                List<ParkingSpot> freeParkingSpotsByCategory = ParkingSpotsProcessor.findFreeParkingSpotsByCategory(freeParkingSpots, category);

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
        }

        return null;
    }

}
