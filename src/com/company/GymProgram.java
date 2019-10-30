package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

/**
 * This is the main program for the FinalAssignment project
 */
public class GymProgram {

    private Menu menu = Menu.getInstance();
    private ArrayList<Customer> customers = new ArrayList<>();
    private static GymClass[] gymClasses = new GymClass[3];

    public GymProgram() {

    }

    /**
     * Generates menu items for the main menu and gets the menu choice from the menu class.
     */
    public void start() {

        addExistingCustomers();
        Menu.MenuItem choice;
        do {
            gymClasses = getClasses();
                choice = Menu.getInstance().showMenuAndGetChoice();
                try {
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
                            System.out.println("-------------\n");
                            for (Customer c : customers) {
                                System.out.println(c.getName() + " is attending " + c.getClassAttending());
                            }
                            input = new Scanner(System.in);
                            System.out.println("\nPress Enter to continue");
                            input.nextLine();
                            break;
                        case SHOW_CUSTOMER:
                            input = new Scanner(System.in);
                            System.out.println("Please enter name of customer: ");
                            String name2 = input.nextLine();
                            showCustomer(name2);
                            break;
                        case SHOW_ALL_CLASSES:
                            for (GymClass gymClass : gymClasses) {
                                System.out.println(gymClass.getClassInfo());
                            }
                            input = new Scanner(System.in);
                            System.out.println("Press Enter to continue");
                            input.nextLine();
                            break;
                        case SHOW_PRICES:
                            PriceListView priceList = new PriceListView();
                            priceList.showPriceList();
                            break;
                        case HELP:
                            System.out.println("Select menu item by entering its corresponding number");
                            input = new Scanner(System.in);
                            System.out.println("Press Enter to continue");
                            input.nextLine();
                            break;
                        case QUIT:
                            FileUtility.saveObject("src/files/customers.ser", customers);
                            break;
                        default:
                            menu.showErrorMessage("Menu item not found.");

                    }
                }catch (Exception e){
                    System.out.println("Invalid input");
                }

        } while (choice != Menu.MenuItem.QUIT);

    }

    /**
     * Creates a list of strings from a file with saved customers.
     *
     * @param fileName name of generated customer file
     * @return list of customers from file
     */
    public static List<Customer> readObjects(String fileName) {
        ObjectInputStream objectinputstream = null;
        List<Customer> customersList = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName);
            objectinputstream = new ObjectInputStream(streamIn);
            customersList = (List<Customer>) objectinputstream.readObject();
            objectinputstream.close();
        } catch (FileNotFoundException e){
            System.out.println("File does not exist in filepath");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return customersList;
    }

    /**
     * Adds a customer to a gym class.
     *
     * @param className name of class the customer is attending
     * @param name      name of the customer
     */
    public void addCustomerToClass(String className, String name) {
        Customer customer = createAndReturnCustomer(className, name);

        int i = 0;
        while (gymClasses[i].getClassName() != className) {
            i++;
        }

        if (customer != null && gymClasses[i].calculateSpotsLeft(gymClasses[i].getTotalSpots(), gymClasses[i].getPplAttending()) > 0) {
            customers.add(customer);
            System.out.println(name + " has been added to " + className);
        } else {
            System.out.println("This class is full, try another one");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Press Enter to continue");
        input.nextLine();

    }

    /**
     * Loads customers from file to array of customers.
     */
    public void addExistingCustomers() {
            String fileName = "src/files/customers.ser";
            try{
                for (Customer c : readObjects(fileName)) {
                    customers.add(c);
                }
            }catch(NullPointerException e){
                System.out.println( "File does not exist" );
            }

    }

    /**
     * Finds a specific customer in the customers array list and writes it to the console.
     *
     * @param name name of the customer
     */
    public void showCustomer(String name) {
        for (Customer c : customers) {
            if (name.equals(c.getName())) {
                System.out.println(c.getName() + " is attending " + c.getClassAttending());

                Scanner input = new Scanner(System.in);
                System.out.println("Press Enter to continue");
                input.nextLine();

            }
        }
    }

    /**
     * Finds and removes a customer from the customer array list.
     *
     * @param index index in the arraylist of the customer to remove.
     */
    public void removeCustomerFromClass(int index) {
        String name = customers.get(index).getName();
        String className = customers.get(index).getClassAttending();
        customers.remove(index);
        System.out.println(name + " has been removed from " + className);
        Scanner input = new Scanner(System.in);
        System.out.println("Press Enter to continue");
        input.nextLine();

    }

    /**
     * Counts the number of customers attending a specific class.
     *
     * @param className name of the gym class
     * @return number of people attending class.
     */
    public int getPplAttendingClass(String className) {
        int attending = 0;
        for (Customer c : customers) {
            if (c.getClassAttending().equals(className)) {
                attending++;
            }
        }
        return attending;
    }

    /**
     * Creates gym classes and adds them to an array.
     *
     * @return array of classes.
     */
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

    /**
     * Creates and returns a customer.
     *
     * @param className name of class customer want to attend
     * @param name      name of the customer
     * @return customer
     */
    public static Customer createAndReturnCustomer(String className, String name) {

        if (className == "Spinning")
            return new Customer(name, "Spinning");
        if (className == "Aerobics")
            return new Customer(name, "Aerobics");
        if (className == "Boxing")
            return new Customer(name, "Boxing");
        else {
            Menu.getInstance().showErrorMessage("Could not create customer: " + className);
            return null;
        }


    }


}

