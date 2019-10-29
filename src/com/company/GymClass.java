package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class GymClass<T> {
    private String time;
    private String name;
    private int totalSpots;
    private int pplAttending;

    public abstract int CalculateSpotsLeft(int totalSpots, int pplAttending);

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
        String classInfo =  time + " " + name + ", " + CalculateSpotsLeft(totalSpots, pplAttending)+ " spots left";
        return classInfo;
    }


}
