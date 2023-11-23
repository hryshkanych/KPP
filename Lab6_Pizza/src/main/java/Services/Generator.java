package Services;

import PizzeriaComponents.Customer;
import PizzeriaComponents.Order;
import PizzeriaComponents.Pizza;
import PizzeriaComponents.Pizzeria;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Generator {

        public static void GeneratePizzas(Pizzeria pizzeria) {
            List<String> margheritaIngredients = Arrays.asList("Tomato Sauce", "Mozzarella", "Basil");
            Pizza margherita = new Pizza("Margherita", 500, 8.99, margheritaIngredients);
            pizzeria.addPizza(margherita);

            List<String> pepperoniIngredients = Arrays.asList("Tomato Sauce", "Mozzarella", "Pepperoni");
            Pizza pepperoni = new Pizza("Pepperoni", 550, 9.99, pepperoniIngredients);
            pizzeria.addPizza(pepperoni);

            List<String> veggieIngredients = Arrays.asList("Tomato Sauce", "Mozzarella", "Bell Pepper", "Olives", "Onions");
            Pizza veggie = new Pizza("Veggie", 450, 7.99, veggieIngredients);
            pizzeria.addPizza(veggie);

            List<String> hawaiianIngredients = Arrays.asList("Tomato Sauce", "Mozzarella", "Ham", "Pineapple");
            Pizza hawaiian = new Pizza("Hawaiian", 550, 10.99, hawaiianIngredients);
            pizzeria.addPizza(hawaiian);

            List<String> quattroFormaggiIngredients = Arrays.asList("Tomato Sauce", "Mozzarella", "Gorgonzola", "Parmesan", "Fontina");
            Pizza quattroFormaggi = new Pizza("Quattro Formaggi", 500, 11.99, quattroFormaggiIngredients);
            pizzeria.addPizza(quattroFormaggi);

            List<String> meatLoversIngredients = Arrays.asList("Tomato Sauce", "Mozzarella", "Pepperoni", "Ham", "Bacon", "Sausage");
            Pizza meatLovers = new Pizza("Meat Lovers", 600, 12.99, meatLoversIngredients);
            pizzeria.addPizza(meatLovers);
        }


    public static void generateCustomers(Pizzeria pizzeria) {
        Random random = new Random();
        List<Pizza> availablePizzas = pizzeria.getPizzas();

        for (int i = 1; i <= 10; i++) {
            String name = "Customer " + i;
            String deliveryAddress = "Main Street " + i;
            Customer customer = new Customer(name, deliveryAddress);

            // Генерація списку піц для одного замовлення
            List<Pizza> pizzasForOrder = IntStream.range(0, random.nextInt(4) + 1)
                    .mapToObj(k -> availablePizzas.get(random.nextInt(availablePizzas.size())))
                    .collect(Collectors.toList());

            LocalDateTime deliveryTime = LocalDateTime.now().minusHours(random.nextInt(7)).plusHours(random.nextInt(6)).plusMinutes(random.nextInt(20));
            customer.addOrder(new Order(pizzasForOrder, deliveryTime));

            pizzeria.addCustomer(customer);
        }
    }
}



