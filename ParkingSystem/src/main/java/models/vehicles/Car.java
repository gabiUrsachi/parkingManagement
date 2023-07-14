package models.vehicles;

import models.User;
import utils.Category;

/**
 * This class is used to establish category for a particular vehicle
 */
public class Car extends Vehicle {
    public Car(User owner) {
        super(owner);
        this.category = Category.MEDIUM;
    }
}
