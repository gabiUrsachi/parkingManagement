package models.vehicles;

import models.User;
import utils.Category;

/**
 * This class is used to establish category for a particular vehicle
 */
public class Truck extends Vehicle {
    public Truck(User owner) {
        super(owner);
        this.category = Category.LARGE;
    }
}
