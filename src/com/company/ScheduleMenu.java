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

    public String showMenuAndGetChoice(Class[] classes) {
        System.out.println("Choose Class:\n-------");
        int i = 1;
        for (Class c : classes) {
            System.out.println(i + " " + c.getClassInfo() );
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int choiceIndex = scanner.nextInt();
        return classes[choiceIndex-1].getClassName();


    }
}



