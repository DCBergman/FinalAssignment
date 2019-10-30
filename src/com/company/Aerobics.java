package com.company;

public class Aerobics extends GymClass {

    public int spotsLeft;
    public int calculateSpotsLeft(int totalSpots, int pplAttending) {
        spotsLeft = totalSpots - pplAttending;
        return spotsLeft;
    }

    public Aerobics(String time, String name, int totalSpots, int pplAttending) {
        super(time, name, totalSpots, pplAttending);
    }



}
