package org.example;
import java.util.Scanner;
import java.util.List;

import static org.example.NecklaceManager.findPebblesByTransparencyRange;
import static org.example.NecklaceManager.sort;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PrintableSystem printableSystem = new PrintableSystem();

        printableSystem.PrintToCreateNecklaces();
        int numOfNecklaces = scanner.nextInt();

        NecklacesGenerator necklacesGenerator = new NecklacesGenerator();
        List<Necklace> necklaces= necklacesGenerator.GenerateCollectionOfNeckleces(numOfNecklaces);

        printableSystem.PrintTextHereNeckleces();
        printableSystem.PrintNecklaces(necklaces);

        printableSystem.PrintSelectionListOfNecklaces(necklaces);
        int necklaceToWork = scanner.nextInt();

        printableSystem.PrintSelectionOfWidthRange();
        printableSystem.PrintToWriteMinTransperency();
        double min = scanner.nextDouble();
        printableSystem.PrintToWriteMaxTransperency();
        double max = scanner.nextDouble();

        findPebblesByTransparencyRange(necklaces, necklaceToWork, min, max);

        while (true) {
            printableSystem.PrintSelectionListOfSorting();
            int chosenSorting = scanner.nextInt();

            if (chosenSorting > 7) {
                System.out.println("Error, the number must be from 0 to 7");
                continue;
            }

            sort(necklaces.get(necklaceToWork), chosenSorting);
            printableSystem.PrintSpecificNecklace(necklaces.get(necklaceToWork), necklaceToWork);
        }

    }
}