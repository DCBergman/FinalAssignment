package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymProgram {

   private Menu menu = Menu.getInstance();
    private ArrayList<Customer> customers = new ArrayList<>();
    private static GymClass[] gymClasses = new GymClass[3];

    public GymProgram() {

    }

    public void start() {

        Menu.MenuItem choice;
        do {
            gymClasses = getClasses();
            choice = Menu.getInstance().showMenuAndGetChoice();
            switch (choice) {
                case BOOK_CLASS:
                    String scheduleChoice = ScheduleMenu.getInstance().showMenuAndGetChoice(gymClasses);
                    Scanner input = new Scanner(System.in);
                    System.out.println("Please enter your name");
                    String name = input.nextLine();
                    addCustomerToClass(scheduleChoice, name);
                    break;
                case REMOVE_FROM_CLASS:
                    int customerIndex = RemoveCustomerMenu.getInstance().showMenuAndGetChoice(customers);
                    removeCustomerFromClass(customerIndex);
                    break;
                case SHOW_ALL_CUSTOMERS:
                    FileUtility.saveObject("src/files/customers.ser", customers, StandardOpenOption.APPEND);
                   for(Customer c: getCustomersFromFile("src/files/customers.ser") ){
                       System.out.println(c.getName() + " " + c.getClassAttending());
                   }
                    break;
                case SHOW_CUSTOMER:
                    input = new Scanner(System.in);
                    System.out.println("Please enter name of customer: ");
                    String name2 = input.nextLine();
                    showCustomer(name2);
                    break;
                case SHOW_ALL_CLASSES:
                    for (GymClass gymClass : gymClasses){
                        System.out.println(gymClass.getClassInfo());
                    }
                    break;
                case SHOW_PRICES:
                    PriceListView priceList = new PriceListView();
                    priceList.showPriceList();
                    if (priceList.goBackToMainMenu() == true) {
                        start();
                    }
                    break;
                case HELP:
                    break;
                case QUIT:
                    FileUtility.saveObject("src/files/customers.ser", customers, StandardOpenOption.APPEND);
                    break;
                default:
                    menu.showErrorMessage("Menu item not found.");

            }

        } while (choice != Menu.MenuItem.QUIT);

    }
    public static List<Customer> getCustomersFromFile(String fileName){
        ObjectInputStream objectinputstream = null;
        List<Customer> customerFile = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName);
            objectinputstream = new ObjectInputStream(streamIn);
            customerFile = (List<Customer>) objectinputstream.readObject();
            objectinputstream .close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerFile;
    }

    public void addCustomerToClass(String className, String name) {
        Customer customer = createAndReturnCustomer(className, name);

        if (customer != null) {
            customers.add(customer);
        }
        System.out.println(name + " has been added to " + className);
        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void showCustomer(String name) {
        for (Customer c : customers) {
            if (name.equals(c.getName())) {
                System.out.println(c.getName() + " is attending " + c.getClassAttending());

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeCustomerFromClass(int index) {
        String name = customers.get(index).getName();
        String className = customers.get(index).getClassAttending();
        customers.remove(index);
        System.out.println(name + " has been removed from " + className);
        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPplAttendingClass(String className) {
        int attending = 0;
        for (Customer c : customers) {
            if (c.getClassAttending() == className) {
                attending++;
            }
        }
        return attending;
    }

    public GymClass[] getClasses() {

        Spinning spinning = new Spinning("10:00", "Spinning", 20, getPplAttendingClass("Spinning"));
        gymClasses[0] = spinning;
        Aerobics aerobics = new Aerobics("14:00", "Aerobics", 30, getPplAttendingClass("Aerobics"));
        gymClasses[1] = aerobics;
        Boxing boxing = new Boxing("17:00", "Boxing", 20, getPplAttendingClass("Boxing"));
        gymClasses[2] = boxing;
        /*classes[3] = aerobics2;
        Spinning spinning2 = new Spinning("20:00", "Spinning",20);
       // addClasses(spinning2);
        classes[4] = spinning2;*/
        return gymClasses;
    }
    public static Customer createAndReturnCustomer(String className, String name) {
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

