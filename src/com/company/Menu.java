package com.company;

import java.util.Scanner;

public class Menu {

        private static Menu instance = null;

        private Menu() {
        }

        public static Menu getInstance() {
            if (instance == null) {
                instance = new Menu();
            }
            return instance;
        }

        public enum MenuItem {
           BOOK_CLASS("Book class"),
            REMOVE_FROM_CLASS("Remove yourself from class"),
            SHOW_PRICES("View price list"),
            SHOW_ALL_CUSTOMERS("Show all customers"),
            SHOW_CUSTOMER("Show specific customer"),
            SHOW_ALL_CLASSES("Show all available classes"),
            HELP("Help"),
            QUIT("Quit the program")
            ;

            private String description;
            MenuItem(String description){
                this.description = description;

            }
        }

        public void showErrorMessage(String errorMessage) {
            System.out.println("Error: " + errorMessage);
        }

        public MenuItem showMenuAndGetChoice() {
            System.out.println("\nWelcome to the Gym! \nPlease make a choice:\n----------");
            int i = 1;
            for (MenuItem menuItem : MenuItem.values()) {
                System.out.println(i + ". " + menuItem.description);
                i++;
            }
            Scanner scanner = new Scanner(System.in);
            int choiceIndex = scanner.nextInt();
            return MenuItem.values()[choiceIndex - 1];


        }
}
