package PizzeriaComponents;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Order implements Serializable {
    private static int nextId = 1; // for unique ID
    private int id;
    private List<Pizza> pizzas;
    private LocalDateTime deliveryTime;

    // Constructor
    public Order(List<Pizza> pizzas, LocalDateTime deliveryTime) {
        this.id = nextId++;
        this.pizzas = pizzas;
        this.deliveryTime = deliveryTime;
    }

    public int getId() {
        return id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        String pizzaNames = pizzas.stream()
                .map(Pizza::getName)
                .collect(Collectors.joining(", "));
        return "Order ID: " + id +
                "\nPizzas: " + pizzaNames +
                "\nDelivery Time: " + deliveryTime;
    }

}
