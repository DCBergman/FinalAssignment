package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Class<T> {
    private String time;
    private String name;
    private int totalSpots;
    private int pplAttending;
    private ArrayList<T> allClasses;
    //array av alla klasser?? med generallisering


    public Class(String time, String name, int totalSpots, int pplAttending) {
        this.time = time;
        this.name = name;
        this.totalSpots = totalSpots;
        this.pplAttending = pplAttending;
    }

    public String getTime() {
        return time;
    }


    public int getTotalSpots() {
        return totalSpots;
    }

    public String getClassName() {
        return name;
    }

    public String getClassInfo() {
        String classInfo =  time + " " + name + " " + " spots left: " + (totalSpots - pplAttending);
        return classInfo;
    }


}
