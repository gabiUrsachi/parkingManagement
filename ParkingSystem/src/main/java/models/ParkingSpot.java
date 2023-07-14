package models;

import utils.Category;

import java.util.Objects;

/**
 * This class is used to maintain details about an existing parking spot
 */
public class ParkingSpot {
    private String spotNumber;
    private Category size;

    public ParkingSpot(String spotNumber, Category size) {
        this.spotNumber = spotNumber;
        this.size = size;
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
