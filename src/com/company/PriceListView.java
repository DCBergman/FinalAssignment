package com.company;

import java.util.Scanner;

public class PriceListView {
    private float pricePerClass = 100;
    private float pricePerMonth = 300;
    private float pricePerYear = 3500;

    public PriceListView() {
    }

    public void showPriceList() {

        System.out.println("\nPrice list\n----------");
        System.out.printf("Regular prices: %nPer class:   %.2fkr %nPer month:   %.2fkr %nPer Year:    %.2fkr%n", pricePerClass, pricePerMonth, pricePerYear);
        System.out.printf("%nStudent prices: %nPer class:   %.2fkr %nPer month:   %.2fkr %nPer Year:    %.2fkr%n", pricePerClass*0.80, pricePerMonth*0.80, pricePerYear*0.80);

        System.out.println("\nTo go back to main menu press 1");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if(input == 1 ){
            goBackToMainMenu();
        }
        else{
            System.out.println("Invalid input");
        }

    }
    public boolean goBackToMainMenu(){
            return true;
    }
}






