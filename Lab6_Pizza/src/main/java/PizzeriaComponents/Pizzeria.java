package PizzeriaComponents;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.AbstractMap;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;



public class Pizzeria implements Serializable {
    private List<Pizza> pizzas;
    private List<Customer> customers;

    public Pizzeria() {
        this.pizzas = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }



    // Method to sort orders by delivery time
    public List<Order> sortOrdersByDeliveryTime() {
        return customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .sorted((o1, o2) -> o1.getDeliveryTime().compareTo(o2.getDeliveryTime()))
                .collect(Collectors.toList());
    }

    // Method to get a list of addresses for customers who ordered more than 2 pizzas
    public List<String> getAddressesForFrequentCustomers() {
        return customers.stream()
                .filter(customer -> customer.getOrders().stream()
                        .mapToInt(order -> order.getPizzas().size())
                        .sum() > 2)
                .map(Customer::getDeliveryAddress)
                .collect(Collectors.toList());
    }

    // Method to find the number of customers who ordered a pizza with a given name
    public long countCustomersWhoOrderedPizza(String pizzaName) {
        return customers.stream()
                .filter(customer -> customer.getOrders().stream()
                        .flatMap(order -> order.getPizzas().stream())
                        .anyMatch(pizza -> pizza.getName().equalsIgnoreCase(pizzaName)))   //equalsIgnoreCase -> ігнорує регістри
                .count();
    }

    //Method to find the name of pizza which was ordered most often
    public String findMostOrderedPizzaNames() {
        Map<Pizza, Long> pizzaCountMap = this.customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getPizzas().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));   // indentity -> що клучем буде сам об'єкт pizza
                                                                                               // Collectors.counting() -> порахувати к-сть елементів у кожній групі
        Long maxOrders = pizzaCountMap.values().stream()
                .max(Long::compare)
                .orElse(0L);

        String mostOrderedPizzas = pizzaCountMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxOrders))
                .map(entry -> entry.getKey().getName())
                .collect(Collectors.joining(", "));

        return mostOrderedPizzas.isEmpty() ? "No pizza orders found" : mostOrderedPizzas;
    }
    // Creating a map with pizzas and its customers
    public Map<Pizza, Set<String>> getPizzaCustomerMap() {
        return this.customers.stream()
                .flatMap(customer -> customer.getOrders().stream()
                        .flatMap(order -> order.getPizzas().stream()
                                .map(pizza -> new AbstractMap.SimpleEntry<>(pizza, customer.getName()))))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())));                      // другий аргумент -> вказує що ми хочемо зібрати в значення множину set
    }

    // Find overdue orders
    public Map<Order, Long> getOverdueOrdersMap() {
        LocalDateTime now = LocalDateTime.now();
        return this.customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getDeliveryTime().isBefore(now))
                .collect(Collectors.toMap(
                        order -> order,
                        order -> java.time.Duration.between(order.getDeliveryTime(), now).toMinutes()
                ));
    }


    @Override
    public String toString() {
        return "Pizzeria{" +
                "pizzas=" + pizzas +
                ", customers=" + customers.stream()
                .map(Customer::getName)
                .collect(Collectors.joining(", ")) +
                '}';
    }
}
