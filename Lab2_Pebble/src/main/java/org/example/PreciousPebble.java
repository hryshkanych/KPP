package org.example;

import org.example.Pebble;
import org.example.PebbleForm;

public class PreciousPebble extends Pebble {
    private double fireIndex;
    private double refractionIndex;

    public PreciousPebble(String name, String mineralStructure, double weight, double price, double transparency,
                          PebbleForm form, double fireIndex, double refractionIndex) {
        super(name, mineralStructure, weight, price, transparency, form);
        this.fireIndex = fireIndex;
        this.refractionIndex = refractionIndex;
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Mineral Structure: " + mineralStructure + "\n" +
                "Weight: " + weight + " carats\n" +
                "Price per carat: $" + price + "\n" +
                "Total Price: $" + totalPrice + "\n" +
                "Transparency: " + transparency + "\n" +
                "Form: " +  form + "\n" +
                "Fire Index: " + fireIndex + "\n" +
                "Refraction Index: " + refractionIndex + "\n";
    }

}