package org.example;
import PizzeriaComponents.Order;
import PizzeriaComponents.Pizza;
import PizzeriaComponents.Pizzeria;
import Services.Generator;
import Services.SerializationService;

import static Services.DataExporter.exportPizzeriaDataToHtml;
import static Services.Generator.GeneratePizzas;
import static Services.Generator.generateCustomers;
import static java.lang.System.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(in);

        Pizzeria pizzeria = SerializationService.deserializePizzeria("pizzeria.ser");
        if (pizzeria == null) {
            pizzeria = new Pizzeria();
            Generator.GeneratePizzas(pizzeria);
            Generator.generateCustomers(pizzeria);
        }

        //Task 1
        List<Order> sortedOrders = pizzeria.sortOrdersByDeliveryTime();
        //Task 2
        List<String> specialAddresses = pizzeria.getAddressesForFrequentCustomers();
        //Task 3
        System.out.println("Please, write any name of pizza and we will print how many customers ordered it: ");
        String nameOfPizza = scanner.nextLine();
        long numberOfCustomers = pizzeria.countCustomersWhoOrderedPizza(nameOfPizza);
        //Task 4
        String coolPizzas = pizzeria.findMostOrderedPizzaNames();
        //Task 5
        Map<Pizza, Set<String>> pizzaCustomerMap = pizzeria.getPizzaCustomerMap();
        //Task 6
        Map<Order, Long> overdueOrdersMap = pizzeria.getOverdueOrdersMap();

        SerializationService.serializePizzeria(pizzeria, "pizzeria.ser");

        exportPizzeriaDataToHtml(pizzeria, sortedOrders, specialAddresses,  nameOfPizza, numberOfCustomers, coolPizzas, pizzaCustomerMap, overdueOrdersMap,"Pizzeria_data.html");


    }
}