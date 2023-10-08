package car;

public class Car implements Comparable<Car> {
    private String model;
    private int cost;
    private int maxSpeed;

    public Car(String model, int cost, int maxSpeed) {
        this.model = model;
        this.cost = cost;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public int getCost() {
        return cost;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String toString()
    {
        return "Model: " + model + ", Cost: " + cost + ", Max speed: " + maxSpeed;
    }

    public int compareTo(Car otherCar) {
        return otherCar.model.compareTo(this.model);
    }

}
