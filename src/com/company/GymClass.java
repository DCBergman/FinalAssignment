package com.company;


public abstract class GymClass<T> {
    private String time;
    private String name;
    private int totalSpots;
    private int pplAttending;

    public abstract int calculateSpotsLeft(int totalSpots, int pplAttending);

    public GymClass(String time, String name, int totalSpots, int pplAttending) {
        this.time = time;
        this.name = name;
        this.totalSpots = totalSpots;
        this.pplAttending = pplAttending;
    }

    public String getClassName() {
        return name;
    }

    public String getClassInfo() {
        String classInfo = time + " " + name + ", " + calculateSpotsLeft(totalSpots, pplAttending) + " spots left";
        return classInfo;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public int getPplAttending() {
        return pplAttending;
    }
}
