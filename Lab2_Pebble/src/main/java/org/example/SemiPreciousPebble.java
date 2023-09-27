package org.example;

import org.example.Pebble;
import org.example.PebbleForm;

public class SemiPreciousPebble extends Pebble {
    private boolean colorVariability;
    private boolean opalescence;
    private double fluorescenceCoef;

    public SemiPreciousPebble(String name, String mineralStructure, double weight, double price, double transparency, PebbleForm form,
                              boolean colorVariability, boolean opalescence, double fluorescenceCoef) {
        super(name, mineralStructure, weight, price, transparency, form);
        this.colorVariability = colorVariability;
        this.opalescence = opalescence;
        this.fluorescenceCoef = fluorescenceCoef;
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Mineral Structure: " + mineralStructure + "\n" +
                "Weight: " + weight + " carats\n" +
                "Price per carat: $" + price + "\n" +
                "Total Price: $" + totalPrice + "\n" +
                "Transparency: " + transparency + "\n" +
                "Form: " + form + "\n" +
                "Color Variability: " + colorVariability + "\n" +
                "Opalescence: " + opalescence + "\n" +
                "Fluorescence Coefficient: " + fluorescenceCoef + "\n";
    }
}