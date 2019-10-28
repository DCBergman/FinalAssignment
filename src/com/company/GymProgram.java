package com.company;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class GymProgram {

    Menu menu = Menu.getInstance();
    ArrayList<Customer> customers = new ArrayList<>();
    private boolean keepGoing;
    private static Class[] classes = new Class[3];
    private int i = 0;

    public GymProgram() {

    }

    public void start() {

        classes = getClasses();
        Menu.MenuItem choice = Menu.getInstance().showMenuAndGetChoice();


        switch (choice) {
            case BOOK_CLASS:
                String scheduleChoice = ScheduleMenu.getInstance().showMenuAndGetChoice(classes);
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
                break;
            case SHOW_CUSTOMER:
                break;
            case SHOW_PRICES:
                PriceListView priceList = new PriceListView();
                priceList.showPriceList();
                if(priceList.goBackToMainMenu()==true){
                    start();
                }
                break;
            case HELP:
                //addAccount(BankAccountFactory.AccountType.SALARY);
                break;
            case QUIT:
                FileUtility.saveObject("src/files/customers.ser", customers, StandardOpenOption.CREATE);
                keepGoing = false;
                break;
            default:
                menu.showErrorMessage("Menu item not found.");

        }


        do {
            start();
        } while (keepGoing);

    }

    public void addCustomerToClass(String className, String name) {
        Customer customer = GymFactory.getCustomer(className, name);

        if (customer != null) {
            customers.add(customer);
        }
        System.out.println(name + " has been added to " + className);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
  /*  public static boolean customerExists(String name){

        for(Customer c : cust){
            if (name == c.getClassName()) {
                return true;
            }
        }
      return false;

    }*/

    public void removeCustomerFromClass(int index) {
        String name = customers.get(index).getName();
        String className = customers.get(index).getClassAttending();
        customers.remove(index);
        System.out.println(name + " has been removed from " + className);
        try {
            Thread.sleep(2500);
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

    public Class[] getClasses() {

        Spinning spinning = new Spinning("10:00", "Spinning", 20, getPplAttendingClass("Spinning"));
        classes[0] = spinning;
        Aerobics aerobics = new Aerobics("14:00", "Aerobics", 30, getPplAttendingClass("Aerobics"));
        classes[1] = aerobics;
        Boxing boxing = new Boxing("17:00", "Boxing", 20, getPplAttendingClass("Boxing"));
        classes[2] = boxing;
        /*classes[3] = aerobics2;
        Spinning spinning2 = new Spinning("20:00", "Spinning",20);
       // addClasses(spinning2);
        classes[4] = spinning2;*/
        return classes;
    }



}

