package org.example;
import java.util.Comparator;
import java.util.Collections;


public class ComporatorFactory {
    public void sortByTotalWeightUsingInnerClass(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), new TotalWeightComparator());
    }

    public void sortByTotalWeightUsingInnerClassReversed(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), new TotalWeightComparator().reversed());
    }

    public static void sortByTotalPriceUsingInnerClass(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), new TotalPriceComparatorInnerClass());
    }

    public static void sortByTotalPriceUsingInnerClassReversed(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), new TotalPriceComparatorInnerClass().reversed());
    }

    public static void sortByNameUsingAnonymousInnerClass(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), new Comparator<Pebble>() {
            @Override
            public int compare(Pebble pebble1, Pebble pebble2) {
                String name1 = pebble1.getName();
                String name2 = pebble2.getName();
                return  name1.compareTo(name2);
            }
        });
    }

    public static void sortByNameUsingAnonymousInnerClassReversed(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), new Comparator<Pebble>(){
            @Override
            public int compare(Pebble pebble1, Pebble pebble2) {
                String name1 = pebble1.getName();
                String name2 = pebble2.getName();
                return  name1.compareTo(name2);
            }
        }.reversed());
    }

    public static void sortByTransperencyUsingLambda(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), (pebble1, pebble2) ->
                Double.compare(pebble1.getTransparency(), pebble2.getTransparency())
        );
    }

    public static void sortByTransperencyUsingLambdaReversed(Necklace necklace) {
        Collections.sort(necklace.getPebbles(), (pebble1, pebble2) ->
                Double.compare(pebble1.getTransparency(), pebble2.getTransparency())
        );

        Collections.reverse(necklace.getPebbles());
    }

    static class TotalPriceComparatorInnerClass implements Comparator<Pebble> {
        @Override
        public int compare(Pebble pebble1, Pebble pebble2) {
            double price1 = pebble1.getTotalPrice();
            double price2 = pebble2.getTotalPrice();
            return Double.compare(price1, price2);
        }
    }

    class TotalWeightComparator implements Comparator<Pebble> {
        @Override
        public int compare(Pebble pebble1, Pebble pebble2) {
            double weight1 = pebble1.getWeight();
            double weight2 = pebble2.getWeight();
            return Double.compare(weight1, weight2);
        }
    }

}