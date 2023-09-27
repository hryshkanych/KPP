package org.example;
import java.util.List;
import java.util.ArrayList;

public class NecklaceManager {

    public static void findPebblesByTransparencyRange(List<Necklace> necklaces, int necklaceToWork, double minTransparency, double maxTransparency) {
        List<Pebble> pebblesToRemove = new ArrayList<>();

        for (Pebble pebble : necklaces.get(necklaceToWork).getPebbles()) {
            if (pebble.getTransparency() < minTransparency || pebble.getTransparency() > maxTransparency) {
                pebblesToRemove.add(pebble);
            }
        }

        for (Pebble pebble : pebblesToRemove) {
            necklaces.get(necklaceToWork).removePebble(pebble);
        }
    }



    public static void sort(Necklace necklace, int chosenSorting) {
        ComporatorFactory factory = new ComporatorFactory();
        switch (chosenSorting) {
            case 0 -> factory.sortByTotalWeightUsingInnerClass(necklace);
            case 1 -> ComporatorFactory.sortByTotalPriceUsingInnerClass(necklace);
            case 2 -> ComporatorFactory.sortByNameUsingAnonymousInnerClass(necklace);
            case 3 -> ComporatorFactory.sortByTransperencyUsingLambda(necklace);
            case 4 -> factory.sortByTotalWeightUsingInnerClassReversed(necklace);
            case 5 -> ComporatorFactory.sortByTotalPriceUsingInnerClassReversed(necklace);
            case 6 -> ComporatorFactory.sortByNameUsingAnonymousInnerClassReversed(necklace);
            case 7 -> ComporatorFactory.sortByTransperencyUsingLambdaReversed(necklace);
            default -> System.out.println("Unknown sort type");
        }
    }
}

