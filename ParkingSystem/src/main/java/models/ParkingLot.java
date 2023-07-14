package models;

import utils.Category;
import utils.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class is used for maintaining parking spots states
 */
public class ParkingLot {
    private HashMap<ParkingSpot, State> availableSpots;

    /**
     * It returns available parking spot for a given category
     *
     * @param category necessary spot size
     * @return available parking spot if exists, or else null
     */
    public ParkingSpot occupySpot(Category category) {
        // get free parking spot by category
        Optional<Map.Entry<ParkingSpot, State>> optionalAvailableSpot = this.availableSpots.entrySet().stream()
                .filter(parkingSpotEntry ->
                        parkingSpotEntry.getKey().getSize().equals(category) &&
                        parkingSpotEntry.getValue().equals(State.FREE)
                )
                .findFirst();

        // if free parking spot exists, then it is used for the given category
        if (optionalAvailableSpot.isPresent()) {
            ParkingSpot availableSpot = optionalAvailableSpot.get().getKey();
            this.availableSpots.put(availableSpot, State.OCCUPIED);

            return availableSpot;
        }

        return null;
    }

    /**
     * It marks as free an occupied parking spot
     *
     * @param parkingSpot occupied spot
     */
    public void releaseSpot(ParkingSpot parkingSpot) {
        this.availableSpots.put(parkingSpot, State.FREE);
    }

    public HashMap<ParkingSpot, State> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(HashMap<ParkingSpot, State> availableSpots) {
        this.availableSpots = availableSpots;
    }
}
