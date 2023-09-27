package org.example;

import java.util.List;

public class PrintableSystem {

    public void PrintNecklaces(List<Necklace> necklaces) {
        int necklaceNum = 0;
        for (Necklace necklace : necklaces) {

            System.out.println("_______________________Necklace "+necklaceNum+"_______________________\n");
            System.out.println("<З    Total weight: "+necklaces.get(necklaceNum).getTotalWeight()+" carats");
            System.out.println("<З    Total price:  "+necklaces.get(necklaceNum).getTotalPrice()+" $\n");
            List<Pebble> tempList = necklace.getPebbles();
            for (int i = 0; i < tempList.size(); i++) {
                System.out.println(tempList.get(i));
            }
            necklaceNum ++;
        }
    }

    public void PrintSelectionListOfNecklaces(List<Necklace> necklaces) {
        System.out.println("Choose which necklace to work with. From 0 to "+(necklaces.size()-1));
    }

    public void PrintSelectionOfWidthRange() {
        System.out.println("Enter the range of transparency: \n");
    }

    public void PrintToWriteMinTransperency() {
        System.out.println("Min value of transparency:");
    }

    public void PrintToWriteMaxTransperency() {
        System.out.println("Max value of transparency:");
    }


    public void PrintSelectionListOfSorting() {
        System.out.println("Choose which characteristic to sort the list by: \n" +
                "0 - WEIGHT                    \n" +
                "1 - PRICE                     \n" +
                "2 - NAME                      \n" +
                "3 - TRANSPERENCY              \n" +
                "4 - WEIGHT REVERSED           \n" +
                "5 - PRICE  REVERSED           \n" +
                "6 - NAME   REVERSED           \n" +
                "7 - TRANSPERENCY  REVERSED    \n" );
    }

    public void PrintToCreateNecklaces() {
        System.out.println("\n<З__________________Lets create necklaces___________________<З\n\n" +
                "Enter the number of Necklaces:");
    }

    public void PrintTextHereNeckleces() {
        System.out.println("__________________Here is the necklaces__________________\n");
    }

    public void PrintSpecificNecklace(Necklace necklace, int necklaceNum) {
        System.out.println("_______________________Necklace "+necklaceNum+"_______________________\n");
        System.out.println("<З    Total weight: "+necklace.getTotalWeight()+" carats");
        System.out.println("<З    Total price:  "+necklace.getTotalPrice()+" $");
        List<Pebble> tempList = necklace.getPebbles();
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(tempList.get(i));
        }
    }


}
