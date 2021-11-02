package com.example.airline.infra.console;

import java.util.Scanner;

public class Input {
    private Input() {}
    private static final Scanner scanner = new Scanner(System.in);
    public static int[][] getAirlineBlockDetails() {
        int noOfBlocks = getNumberInput("Enter the number of blocks: ");
        int[][] blocks = new int[noOfBlocks][2];
        for (int i = 0; i < noOfBlocks; i++) {
            int noOfRows = getNumberInput("Number of rows in block " + i + " : ");
            int noOfColumns = getNumberInput("Number of columns in block " + i + " : ");
            int[] block = new int[] {noOfColumns, noOfRows};
            blocks[i] = block;
        }
        return blocks;
    }
    public static int getNoOfPassengers() {
        return getNumberInput("Enter the number of passengers: ");
    }

    private static int getNumberInput(String promptMessage) {
        System.out.print(promptMessage);
        while(!scanner.hasNextInt()) {
            System.out.print("Invalid Input, Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
