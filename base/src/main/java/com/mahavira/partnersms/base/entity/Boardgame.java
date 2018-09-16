package com.mahavira.partnersms.base.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mahavira.partnersms.base.BR;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by norman on 17/07/18.
 *
 */

@Parcel
public class Boardgame extends BaseObservable {
    String name;
    int quantity;
    List<String> components;
    String numPlayers;
    String gameTime;
    String releaseYear;
    String playerAge;

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

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public String getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(final String numPlayers) {
        this.numPlayers = numPlayers;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(final String gameTime) {
        this.gameTime = gameTime;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(final String playerAge) {
        this.playerAge = playerAge;
    }
}
