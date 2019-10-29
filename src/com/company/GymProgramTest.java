package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class GymProgramTest {


    @Test
    public void getClasses() {

        Spinning spinning = new Spinning("10:00", "Spinning", 20, 12);
        Aerobics aerobics = new Aerobics("14:00", "Aerobics", 30, 14);
        Boxing boxing = new Boxing("17:00", "Boxing", 20, 11);
        GymClass[] testArray = {spinning, aerobics, boxing};
        assertFalse(testArray.length == 4);

        Spinning spinning2 = new Spinning("10:00", "Spinning", 20, 12);
        Aerobics aerobics2 = new Aerobics("14:00", "Aerobics", 30, 14);
        Boxing boxing2 = new Boxing("17:00", "Boxing", 20, 11);
        GymClass[] testArray2 = {spinning2, aerobics2, boxing2};
        assertTrue(testArray2.length == 3);



    }
}