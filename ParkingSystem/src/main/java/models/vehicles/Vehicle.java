package models.vehicles;

import models.User;
import utils.Category;

/**
 * This class is used to maintain vehicle details
 */
public abstract class Vehicle {
    protected Category category;
    private User owner;

    public Vehicle(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
