package Transport;

public class TransportType implements Comparable<TransportType> {
    private String name;
    private double speed; // швидкість (км/год)
    private double pricePer100Km; // ціна за 100 км (у вашій валюті)

    public TransportType(String name, double speed, double pricePer100Km) {
        this.name = name;
        this.speed = speed;
        this.pricePer100Km = pricePer100Km;
    }

    public String getName() {
        return name;
    }
    public double getSpeed() {
        return speed;
    }
    public double getPricePer100Km() {
        return pricePer100Km;
    }

    public int compareTo(TransportType other) {
        return Double.compare(other.speed, this.speed);
    }
}

