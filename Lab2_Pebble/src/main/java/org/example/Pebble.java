package org.example;

public class Pebble {
    protected String name;
    protected String mineralStructure;
    protected double weight;
    protected double price; // for one karat
    protected double totalPrice; // for whole pebble
    protected double transparency; // from 0 to 1

    protected PebbleForm form;

    public Pebble(String name, String mineralStructure, double weight, double price, double transparency, PebbleForm form) {
        this.name = name;
        this.mineralStructure = mineralStructure;
        this.weight = weight;
        this.price = price;
        this.transparency = transparency;
        this.form = form;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return weight * price + form.getPrice();
    }

    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public double getTransparency(){
        return transparency;
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Mineral Structure: " + mineralStructure + "\n" +
                "Weight: " + weight + " carats\n" +
                "Price per carat: $" + price + "\n" +
                "Total Price: $" + totalPrice + "\n" +
                "Transparency: " + transparency + "\n" +
                "Form: " + form + "\n";
    }


}


