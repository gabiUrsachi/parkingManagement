package models.vehicles;

import models.User;
import utils.Category;

/**
 * This class is used to establish category for a particular vehicle
 */
public class Motorcycle extends Vehicle {
    public Motorcycle(User owner) {
        super(owner);
        this.category = Category.SMALL;
    }
}
