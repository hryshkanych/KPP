package org.example;

public enum PebbleForm {
    ROUND(100.0),
    OVAL(120.0),
    PEAR(110.0),
    HEART(130.0),
    EMERALD(150.0),
    PRINCESS(140.0),
    MARQUISE(125.0),
    RADIANT(135.0),
    CUSHION(125.0),
    ASSCHER(145.0),
    BAGUETTE(115.0),
    TRILLION(110.0),
    HEXAGONAL(120.0),
    OCTAGONAL(130.0);

    private final double price;

    PebbleForm(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
