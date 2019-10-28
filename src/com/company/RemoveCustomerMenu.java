package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class RemoveCustomerMenu {
    private static com.company.RemoveCustomerMenu instance = null;

    private RemoveCustomerMenu() {
    }

    public static com.company.RemoveCustomerMenu getInstance() {
        if (instance == null) {
            instance = new com.company.RemoveCustomerMenu();
        }
        return instance;
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public int showMenuAndGetChoice(ArrayList<Customer> customers) {
        System.out.println("Choose Class:\n-------");
        int i = 1;
        for (Customer c : customers) {
            System.out.println(i + ". " +c.getName() + " " + c.getClassAttending());
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int choiceIndex = scanner.nextInt();
        return choiceIndex - 1;
    }
}








