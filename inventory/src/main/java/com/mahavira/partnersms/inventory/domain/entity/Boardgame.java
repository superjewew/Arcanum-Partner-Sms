package com.mahavira.partnersms.inventory.domain.entity;

/**
 * Created by norman on 17/07/18.
 */

public class Boardgame {
    String name;
    int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
