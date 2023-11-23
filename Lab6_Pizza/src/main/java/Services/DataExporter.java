package Services;
import PizzeriaComponents.Customer;
import PizzeriaComponents.Order;
import PizzeriaComponents.Pizza;
import PizzeriaComponents.Pizzeria;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DataExporter {
    public static void exportPizzeriaDataToHtml(Pizzeria pizzeria, List<Order> sortedOrders, List<String> specialAddresses, String nameOfPizza,
                                                long numberOfCustomers, String coolPizza, Map<Pizza, Set<String>> pizzaCustomerMap,
                                                Map<Order, Long> overdueOrdersMap, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.write("<html><head><style>");
            writer.write("body { background-color: #ede8f5; font-family: Arial, sans-serif; }");
            writer.write("table { border-collapse: collapse; width: 80%; margin-left: auto; margin-right: auto; }");
            writer.write("th, td { border: 2px solid black; padding: 8px; text-align: left; }");
            writer.write("th { background-color: #B2A6D9; }");
            writer.write("td { background-color: #FEE8FE; }");
            writer.write("h2 { text-align: center; }");
            writer.write("h3 { text-align: center; color: #717171; }");
            writer.write("</style></head><body>");

            // table for pizzas
            writer.write("<h2>Pizzas</h2>");
            writer.write("<table>");
            writer.write("<tr><th>Name</th><th>Weight</th><th>Price</th><th>Ingredients</th></tr>");
            for (Pizza pizza : pizzeria.getPizzas()) {
                writer.write(String.format(
                        "<tr><td>%s</td><td>%f</td><td>%f</td><td>%s</td></tr>",
                        pizza.getName(), pizza.getWeight(), pizza.getPrice(),
                        String.join(", ", pizza.getIngredients())
                ));
            }
            writer.write("</table><br>");

            // table for customers
            writer.write("<h2>Customers</h2>");
            writer.write("<table>");
            writer.write("<tr><th>Name</th><th>Delivery Address</th><th>Order ID</th><th>Orders</th><th>Delivery Time</th></tr>");
            for (Customer customer : pizzeria.getCustomers()) {
                for (Order order : customer.getOrders()) {
                    writer.write("<tr>");
                    writer.write(String.format(
                            "<td>%s</td><td>%s</td><td>%d</td><td>%s</td><td>%s</td>",
                            customer.getName(), customer.getDeliveryAddress(), order.getId(),
                            order.getPizzas().stream().map(Pizza::getName).collect(Collectors.joining(", ")),
                            order.getDeliveryTime().toString()
                    ));
                    writer.write("</tr>");
                }
                if (customer.getOrders().isEmpty()) {
                    writer.write("<td colspan='5'>No orders</td>");
                }
            }
            writer.write("</table><br>");

            // table for sorted list of orders
            writer.write("<h2>Additional information</h2>");
            writer.write("<h3>Orders sorted by delivery time</h3>");
            writer.write("<table border='1'>");
            writer.write("<tr style='background-color:lightblue;'><th>Order ID</th><th>Orders</th><th>Delivery Time</th></tr>");
            for (Order order : sortedOrders) {
                String pizzasNames = order.getPizzas().stream()
                        .map(Pizza::getName)
                        .collect(Collectors.joining(", "));
                writer.write(String.format(
                        "<tr><td>%d</td><td>%s</td><td>%s</td></tr>",
                        order.getId(), pizzasNames, order.getDeliveryTime().toString()
                ));
            }
            writer.write("</table><br>");

            // table for list of addresses of customers who ordered more than 2 pizzas
            writer.write("<h3>List of addresses for customers who ordered more than 2 pizzas</h3>");
            writer.write("<table border='1'>");
            writer.write("<tr style='background-color:lightblue;'><th>Address</th></tr>");
            for (String address : specialAddresses) {
                writer.write(String.format("<tr><td>%s</td></tr>", address));
            }
            writer.write("</table><br>");

            // table of number of users who ordered a pizza with a given name
            writer.write("<h3>The number of users who ordered a pizza with a given name</h3>");
            writer.write("<table border='1'>");
            writer.write("<tr style='background-color:lightblue;'><th>Pizza Name</th><th>Number of Customers</th></tr>");
            writer.write(String.format("<tr><td>%s</td><td>%d</td></tr>", nameOfPizza, numberOfCustomers));
            writer.write("</table><br>");

            // table of the most ordered pizza
            writer.write("<h3>The most ordered pizza</h3>");
            writer.write("<table border='1'>");
            writer.write("<tr style='background-color:lightblue;'><th>Pizza Name</th></tr>");
            writer.write(String.format("<tr><td>%s</td></tr>", coolPizza));
            writer.write("</table><br>");

            // table for displaying map of pizza and its customers
            writer.write("<h3>Pizza Customer Map</h3>");
            writer.write("<table border='1'>");
            writer.write("<tr style='background-color:lightblue;'><th>Pizza</th><th>Customers</th></tr>");
            for (Map.Entry<Pizza, Set<String>> entry : pizzaCustomerMap.entrySet()) {
                writer.write(String.format(
                        "<tr><td>%s</td><td>%s</td></tr>",
                        entry.getKey().getName(),
                        String.join(", ", entry.getValue())
                ));
            }
            writer.write("</table><br>");

            // table for overdue orders
            writer.write("<h3>Overdue Orders</h3>");
            writer.write("<table border='1'>");
            writer.write("<tr style='background-color:lightblue;'><th>Order ID</th><th>Orders</th><th>Overdue Time (minutes)</th></tr>");
            for (Map.Entry<Order, Long> entry : overdueOrdersMap.entrySet()) {
                String pizzaNames = entry.getKey().getPizzas().stream()
                        .map(Pizza::getName)
                        .collect(Collectors.joining(", "));
                writer.write(String.format(
                        "<tr><td>%d</td><td>%s</td><td>%d</td></tr>",
                        entry.getKey().getId(), pizzaNames, entry.getValue()
                ));
            }
            writer.write("</table><br>");

            writer.write("</body></html>");
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }
}
