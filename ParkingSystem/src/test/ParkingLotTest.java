import models.ParkingLot;
import models.ParkingSpot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Category;
import utils.State;

import java.util.HashMap;

class ParkingLotTest {
    private static final ParkingLot parkingLot = new ParkingLot();

    @BeforeEach
    public  void init(){
        HashMap<ParkingSpot, State> availableSpots = new HashMap<>();
        availableSpots.put(new ParkingSpot("A12", Category.LARGE), State.FREE);
        availableSpots.put(new ParkingSpot("B12", Category.MEDIUM), State.FREE);
        availableSpots.put(new ParkingSpot("C12", Category.SMALL), State.FREE);
        availableSpots.put(new ParkingSpot("D12", Category.LARGE), State.FREE);

        parkingLot.setAvailableSpots(availableSpots);
    }

    @Test
    void failForTryingToOccupyMoreSpotsThanAvailableOnes() {
        Assertions.assertNotNull(parkingLot.occupySpot(Category.LARGE));
        Assertions.assertNotNull(parkingLot.occupySpot(Category.LARGE));

        Assertions.assertNull(parkingLot.occupySpot(Category.LARGE));
    }

    @Test
    void anOccupiedSpotIsAvailableAgainAfterRelease() {
        ParkingSpot occupiedSpot1 = parkingLot.occupySpot(Category.SMALL);
        Assertions.assertNotNull(occupiedSpot1);

        ParkingSpot occupiedSpot2 = parkingLot.occupySpot(Category.SMALL);
        Assertions.assertNull(occupiedSpot2);

        parkingLot.releaseSpot(occupiedSpot1);

        occupiedSpot2 = parkingLot.occupySpot(Category.SMALL);
        Assertions.assertNotNull(occupiedSpot2);
    }
}