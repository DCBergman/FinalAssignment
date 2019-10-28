package com.company;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String classAttending;

    public Customer(String name, String classAttending)  {
        this.name = name;
        this.classAttending = classAttending;
    }

    public String getName() {
        return name;
    }

    public String getClassAttending() {
        return classAttending;
    }
}
