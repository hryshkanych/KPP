package PizzeriaComponents;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Pizza implements Serializable {
    private String name;
    private double weight;
    private double price;
    private List<String> ingredients;

    public Pizza(String name, double weight, double price, List<String> ingredients) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.ingredients = new ArrayList<>(ingredients);
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }

    @Override
    public String toString() {
        return  "name=" + name +
                "\nweight=" + weight +
                "\nprice=" + price +
                "\ningredients=" + ingredients;

    }
}
