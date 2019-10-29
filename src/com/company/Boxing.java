package com.company;

public class Boxing extends GymClass {

    private int spotsLeft;
    @Override
    public int CalculateSpotsLeft(int totalSpots, int pplAttending) {
        spotsLeft = totalSpots - pplAttending;
        return spotsLeft;
    }

    public Boxing(String time, String name, int totalSpots, int pplAttending) {
        super(time, name, totalSpots, pplAttending);
    }


}
