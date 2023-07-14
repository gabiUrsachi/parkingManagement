package models;

import utils.Category;
import utils.Type;

import java.util.Objects;

/**
 * This class is used to maintain details about an existing parking spot
 */
public class ParkingSpot {
    private String spotNumber;
    private Category size;
    private Type type = Type.NON_ELECTRICAL;

    public ParkingSpot(String spotNumber, Category size) {
        this.spotNumber = spotNumber;
        this.size = size;
    }

    public ParkingSpot(String spotNumber, Category size, Type type) {
        this.spotNumber = spotNumber;
        this.size = size;
        this.type = type;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Category getSize() {
        return size;
    }

    public void setSize(Category size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return Objects.equals(spotNumber, that.spotNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotNumber);
    }
}
