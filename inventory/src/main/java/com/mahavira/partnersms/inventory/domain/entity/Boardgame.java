package com.mahavira.partnersms.inventory.domain.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mahavira.partnersms.inventory.BR;

/**
 * Created by norman on 17/07/18.
 *
 */

public class Boardgame extends BaseObservable {
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    public void addQuantity() {
        quantity++;
        notifyPropertyChanged(BR.quantity);
    }

    public void reduceQuantity() {
        quantity--;
        notifyPropertyChanged(BR.quantity);
    }
}
