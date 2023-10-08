package org.example;
import car.AutoRiaManager;
import car.AutoRia;
import car.AutoRiaManager;
import car.Car;
import services.FileReaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import static services.FileReaderService.readCarListFromFile;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstFilePath = "D:\\My creation\\intllij_Java\\Uni\\Lab3_Cars\\src\\data\\CarsHere.txt";
        String secondFilePath = "D:\\My creation\\intllij_Java\\Uni\\Lab3_Cars\\src\\data\\CarsHereNew.txt";
        AutoRia autoRia = new AutoRia(readCarListFromFile(firstFilePath), readCarListFromFile(secondFilePath));
        AutoRiaManager autoRiaManager = new AutoRiaManager();
        System.out.println(autoRia);

        //TASK 1
        System.out.println("_________Lets create a Map ( key- speed, value - List of cars )_________");
        System.out.println("How many cars do you want to print for each speed");
        int numberOfCars = scanner.nextInt();
        autoRia.SetNumberOfCarsToPrint(numberOfCars);
        autoRia.SetWhatToPrint(1);
        System.out.println(autoRia);

        //TASK 2
        System.out.println("_________Lets delete from Map some cars that are certain models_________");
        System.out.println("What car models do you want to delete");
        String models = reader.readLine();
        String[] modelArray = models.split(",");
        autoRiaManager.deleteCertainCars(modelArray, autoRia.getFirstSpeedMap(), autoRia.getSecondSpeedMap()); //CREATED FUNCTION
        System.out.println("Thank you! How many cars do you want to print for each speed");
        numberOfCars = scanner.nextInt();
        autoRia.SetNumberOfCarsToPrint(numberOfCars);
        System.out.println(autoRia);

        //TASK 3
        System.out.println("_________Lets work with combined list (first list + second list)_________");
        int numToContinue;
        while (true) {
            System.out.println("Type 1 to continue");
            if (scanner.hasNextInt()) {
                numToContinue = scanner.nextInt();
                if (numToContinue == 1) {
                    break;
                } else {
                    System.out.println("You typed incorrect number. Try again. Type 1 to continue");
                }
            } else {
                System.out.println("Type 1 to continue");
                scanner.next();
            }
        }
        autoRia.SetWhatToPrint(2);
        autoRiaManager.SortCombinedList(autoRia.getCombinedCarList());
        System.out.println(autoRia);

        System.out.println("Please type the price diapason to count cars in it");
        System.out.println("Min price value:");
        int minPriceValue = scanner.nextInt();
        System.out.println("Max price value:");
        int maxPriceValue = scanner.nextInt();
        autoRiaManager.findCarsInPriceRange(minPriceValue, maxPriceValue, autoRia.getCombinedCarList());


    }
}