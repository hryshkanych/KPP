package PizzeriaComponents;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String deliveryAddress;
    private List<Order> orders;

    public Customer(String name, String deliveryAddress) {
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name)
                .append("\nDelivery Address: '").append(deliveryAddress).append('\'');

        if (!orders.isEmpty()) {
            sb.append("\nOrders:");
            for (Order order : orders) {
                sb.append("\n").append(order);
            }
        } else {
            sb.append("\nNo orders");
        }

        return sb.toString();
    }
}
