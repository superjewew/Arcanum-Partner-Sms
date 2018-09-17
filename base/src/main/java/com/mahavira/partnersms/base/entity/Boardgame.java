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
    String numPlayersFrom;
    String numPlayersTo;
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

    @Bindable
    public String getNumPlayersFrom() {
        return numPlayersFrom;
    }

    public void setNumPlayersFrom(final String numPlayersFrom) {
        this.numPlayersFrom = numPlayersFrom;
        notifyPropertyChanged(BR.numPlayersFrom);
    }

    @Bindable
    public String getNumPlayersTo() {
        return numPlayersTo;
    }

    public void setNumPlayersTo(final String numPlayersTo) {
        this.numPlayersTo = numPlayersTo;
        notifyPropertyChanged(BR.numPlayersTo);
    }

    @Bindable
    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(final String gameTime) {
        this.gameTime = gameTime;
        notifyPropertyChanged(BR.gameTime);
    }

    @Bindable
    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final String releaseYear) {
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public String getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(final String playerAge) {
        this.playerAge = playerAge;
        notifyPropertyChanged(BR.playerAge);
    }
}
