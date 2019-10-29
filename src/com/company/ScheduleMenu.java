package com.company;

import java.util.Scanner;

public class ScheduleMenu {

    private static com.company.ScheduleMenu instance = null;

    private ScheduleMenu() {
    }

    public static com.company.ScheduleMenu getInstance() {
        if (instance == null) {
            instance = new com.company.ScheduleMenu();
        }
        return instance;
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public String showMenuAndGetChoice(GymClass[] gymClasses) {
        System.out.println("Choose Class:\n-------");
        int i = 1;
        for (GymClass c : gymClasses) {
            System.out.println(i + " " + c.getClassInfo() );
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int choiceIndex = scanner.nextInt();
        return gymClasses[choiceIndex-1].getClassName();


    }
}



