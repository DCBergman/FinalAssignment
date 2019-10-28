package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GymFactory {

  /*  public enum ClassType {
        SPINNING,
        AEROBICS,
        BOXING,
    }*/

    /* public static Class createClass(ClassType classType) {
         switch (classType) {
             case SPINNING:
                 return new Spinning("10:00", "Anna Larsson", 30 );
             case AEROBICS:
                 return new Aerobics(1.5);
             case BOXING:
                 return new Boxing();
             default:
                 Menu.getInstance().showErrorMessage("Could not create account of type: " + classType.toString());
                 return null;


         }

     }*/
    public static Customer getCustomer(String className, String name) {
        if (className == "Spinning")
            return new Customer(name, "Spinning");
        if (className == "Aerobics")
            return new Customer(name, "Aerobics");
        if (className == "Boxing")
            return new Customer(name, "Boxing");
        else {
            Menu.getInstance().showErrorMessage("Could not create account of type: " + className);
            return null;
        }


    }

}


