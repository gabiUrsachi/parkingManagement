package models.vehicles;

import models.User;
import utils.Category;
import utils.Type;

/**
 * This class is used to maintain vehicle details
 */
public abstract class Vehicle {
    protected Category category;
    private User owner;
    private Type type = Type.NON_ELECTRICAL;

    public Vehicle(User owner) {
        this.owner = owner;
    }

    public Vehicle(Type type, User owner) {
        this.type = type;
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
