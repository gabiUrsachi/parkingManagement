package services;

import models.ParkingLot;
import models.ParkingSpot;
import utils.Category;
import utils.Type;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is used for querying parking spots objects properties
 */
public class ParkingSpotsProcessor {
    /**
     * It filters non-occupied parking spots
     *
     * @param parkingLot an object that contains all existing parking spots
     * @return free parking spots
     */
    public static List<ParkingSpot> findFreeParkingSpots(ParkingLot parkingLot) {
        return parkingLot.getSpotVehicleAssociations()
                .entrySet()
                .stream()
                .filter(parkingSpotVehicleEntry -> parkingSpotVehicleEntry.getValue() == null)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * It filters parking spots that have a particular type
     *
     * @param parkingSpots parking spots to be filtered
     * @param type         filter parameter
     * @return all existing spots for given type
     */
    public static List<ParkingSpot> findFreeParkingSpotsByType(List<ParkingSpot> parkingSpots, Type type) {
        return parkingSpots
                .stream()
                .filter(parkingSpot -> parkingSpot.getType().equals(type))
                .collect(Collectors.toList());
    }

    /**
     * It filters parking spots that have a particular category
     *
     * @param parkingSpots parking spots to be filtered
     * @param category     filter parameter
     * @return all existing spots for given category
     */
    public static List<ParkingSpot> findFreeParkingSpotsByCategory(List<ParkingSpot> parkingSpots, Category category) {
        return parkingSpots
                .stream()
                .filter(parkingSpot -> parkingSpot.getSize().equals(category))
                .collect(Collectors.toList());
    }

    /**
     * It verifies that a list of parking spots is not empty
     *
     * @param freeParkingSpots parking spots list to be tested
     * @return true for non-empty list, otherwise false
     */
    public static boolean foundFreeSpots(List<ParkingSpot> freeParkingSpots) {
        return !freeParkingSpots.isEmpty();
    }
}
