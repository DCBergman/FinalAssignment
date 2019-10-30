package com.company;

public class Spinning extends GymClass {

    public int spotsLeft;
    public int calculateSpotsLeft(int totalSpots, int pplAttending) {
        spotsLeft = totalSpots - pplAttending;
        return spotsLeft;
    }

    public Spinning(String time, String name, int totalSpots, int pplAttending) {
        super(time, name, totalSpots, pplAttending);
    }



}
