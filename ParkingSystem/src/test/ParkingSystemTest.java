import models.ParkingLot;
import models.ParkingSpot;
import models.User;
import models.vehicles.Car;
import models.vehicles.Motorcycle;
import models.vehicles.Truck;
import models.vehicles.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ParkingSystem;
import utils.Category;
import utils.State;
import utils.UserRole;

import java.util.HashMap;

class ParkingSystemTest {
    private final ParkingLot parkingLot = new ParkingLot();
    private ParkingSystem parkingSystem;
    private User user;
    private Vehicle vehicle;

    @BeforeEach
    public  void init(){
        HashMap<ParkingSpot, State> availableSpots = new HashMap<>();
        availableSpots.put(new ParkingSpot("A12", Category.LARGE), State.FREE);
        availableSpots.put(new ParkingSpot("B12", Category.MEDIUM), State.FREE);
        availableSpots.put(new ParkingSpot("C12", Category.SMALL), State.FREE);
        availableSpots.put(new ParkingSpot("D12", Category.LARGE), State.FREE);

        parkingLot.setAvailableSpots(availableSpots);
        parkingSystem = new ParkingSystem(parkingLot);
        user = new User("User1", UserRole.REGULAR);
    }

    @Test
    void regularUserSuccessfullyParksSmallVehicle() {
        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot = parkingSystem.park(vehicle);

        Assertions.assertEquals("C12", parkingSpot.getSpotNumber());
    }

    @Test
    void regularUserFailsParkingSmallVehicle() {

        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot1 = parkingSystem.park(vehicle);

        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot2 = parkingSystem.park(vehicle);

        Assertions.assertEquals("C12", parkingSpot1.getSpotNumber());
        Assertions.assertNull(parkingSpot2);
    }

    @Test
    void vipUserSuccessfullyParksSmallVehicle() {
        user.setRole(UserRole.VIP);

        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot1 = parkingSystem.park(vehicle);

        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot2 = parkingSystem.park(vehicle);

        Assertions.assertEquals("C12", parkingSpot1.getSpotNumber());
        Assertions.assertEquals("B12", parkingSpot2.getSpotNumber());
    }

    @Test
    void vipUserCannotParkLargeVehicleOnSmallSizeSpot() {
        user.setRole(UserRole.VIP);

        vehicle = createVehicle(Category.LARGE);
        ParkingSpot parkingSpot1 = parkingSystem.park(vehicle);

        vehicle = createVehicle(Category.LARGE);
        ParkingSpot parkingSpot2 = parkingSystem.park(vehicle);

        vehicle = createVehicle(Category.LARGE);
        ParkingSpot parkingSpot3 = parkingSystem.park(vehicle);

        Assertions.assertEquals("A12", parkingSpot1.getSpotNumber());
        Assertions.assertEquals("D12", parkingSpot2.getSpotNumber());
        Assertions.assertNull(parkingSpot3);
    }

    private Vehicle createVehicle(Category category){
        switch (category){
            case SMALL:
                return new Motorcycle(user);
            case MEDIUM:
                return new Car(user);
            default:
                return new Truck(user);
        }
    }
}