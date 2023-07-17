package services.strategy;

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
import utils.Category;
import utils.Type;
import utils.UserRole;

import java.util.HashMap;

class ParkingStrategyTest {
    private final ParkingLot parkingLot = new ParkingLot();

    private ParkingStrategy parkingStrategy;
    private User user;
    private Vehicle vehicle;

    @BeforeEach
    public  void init(){
        HashMap<ParkingSpot, Vehicle> spotVehicleAssociations = new HashMap<>();
        spotVehicleAssociations.put(new ParkingSpot("A12", Category.LARGE), null);
        spotVehicleAssociations.put(new ParkingSpot("B12", Category.MEDIUM), null);
        spotVehicleAssociations.put(new ParkingSpot("C12", Category.SMALL), null);
        spotVehicleAssociations.put(new ParkingSpot("D12", Category.LARGE), null);

        parkingLot.setSpotVehicleAssociations(spotVehicleAssociations);
        user = new User("User1", UserRole.REGULAR);
    }

    @Test
    void regularUserSuccessfullyParksSmallVehicle() {
        parkingStrategy = new RegularParkingStrategy(parkingLot);

        vehicle = createVehicle(Category.SMALL);
        vehicle.setType(Type.ELECTRICAL);
        ParkingSpot parkingSpot = parkingStrategy.park(vehicle);

        Assertions.assertEquals("C12", parkingSpot.getSpotNumber());
    }

    @Test
    void regularUserFailsParkingSmallVehicle() {
        parkingStrategy = new RegularParkingStrategy(parkingLot);

        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot1 = parkingStrategy.park(vehicle);

        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot2 = parkingStrategy.park(vehicle);

        Assertions.assertEquals("C12", parkingSpot1.getSpotNumber());
        Assertions.assertNull(parkingSpot2);
    }

    @Test
    void vipUserSuccessfullyParksElectricalSmallVehicle() {
        parkingStrategy = new VIPParkingStrategy(parkingLot);
        user.setRole(UserRole.VIP);

        vehicle = createVehicle(Category.SMALL);
        vehicle.setType(Type.ELECTRICAL);
        ParkingSpot parkingSpot1 = parkingStrategy.park(vehicle);

        vehicle = createVehicle(Category.SMALL);
        ParkingSpot parkingSpot2 = parkingStrategy.park(vehicle);

        Assertions.assertEquals("C12", parkingSpot1.getSpotNumber());
        Assertions.assertEquals("B12", parkingSpot2.getSpotNumber());
    }

    @Test
    void vipUserCannotParkLargeVehicleOnSmallSizeSpot() {
        parkingStrategy = new VIPParkingStrategy(parkingLot);
        user.setRole(UserRole.VIP);

        vehicle = createVehicle(Category.LARGE);
        ParkingSpot parkingSpot1 = parkingStrategy.park(vehicle);

        vehicle = createVehicle(Category.LARGE);
        ParkingSpot parkingSpot2 = parkingStrategy.park(vehicle);

        vehicle = createVehicle(Category.LARGE);
        ParkingSpot parkingSpot3 = parkingStrategy.park(vehicle);

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