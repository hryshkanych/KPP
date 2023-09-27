package org.example;

import java.util.List;
import java.util.ArrayList;

public class Necklace {
    private List<Pebble> pebbles;
    private double totalWeight;
    private double totalPrice;

    public Necklace() {
        pebbles = new ArrayList<>();
        totalWeight = 0;
        totalPrice = 0;
    }

    public void addPebble(Pebble newPebble) {
        pebbles.add(newPebble);

        totalWeight += newPebble.getWeight();
        totalPrice += newPebble.getTotalPrice();
    }

    public void removePebble(Pebble pebbleRemove) {
        pebbles.remove(pebbleRemove);

        totalWeight -= pebbleRemove.getWeight();
        totalPrice -= pebbleRemove.getTotalPrice();
    }



    public double getTotalWeight() {
        return totalWeight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Pebble> getPebbles() {
        return pebbles;
    }

    public void setPebbles(List<Pebble> pebbles) {
        this.totalPrice = 0;
        this.totalWeight = 0;
        this.pebbles = pebbles;

        for (Pebble pebble : pebbles){
            totalWeight += pebble.getWeight();
            totalPrice += pebble.getTotalPrice();
        }
    }

    public String toSting() {
        return "Total weight: " + totalWeight + "\n" +
                "Total price: " + totalPrice + "\n";
    }

}