package org.example;

import Calculator.PathCalculator;
import City.City;
import Transport.TransportType;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import static Calculator.PriceCalculator.calculatePriceForWholePath;
import static Calculator.TimeCalculator.calculateArrivalTimeForPath;
import static Calculator.TimeCalculator.calculateTimeForWholePath;
import static Calculator.TransportCalculator.calculateOptimalTransport;
import static Transport.TransportTypeGenerator.generateTransportTypes;
import City.CityGenerator;
import java.time.ZonedDateTime;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Map<String, City> cities = new HashMap<>();
        cities = CityGenerator.GenerateCities();
        Scanner scanner = new Scanner(System.in);
        boolean isStops = false;

        //_________________________________Cities__________________________________________
        System.out.println("Available cities:");
        for (String cityName : cities.keySet()) {
            System.out.println(cityName);
        }

        System.out.print("Enter the starting city: ");
        String startCity = scanner.nextLine();
        System.out.print("Enter the destination city: ");
        String endCity = scanner.nextLine();

        System.out.print("Would you like to add stops along the route? (Yes/No): ");
        String addStops = scanner.nextLine();
        List<String> stops = new ArrayList<>();
        if (addStops.equalsIgnoreCase("Yes")) {
            isStops = true;
            System.out.print("Enter stops separated by a comma (e.g., Warshaw, Berlin): ");
            String stopsInput = scanner.nextLine();
            stops.addAll(Arrays.asList(stopsInput.split(", ")));
        } else {

        }
        //___________________________________Time_________________________________________
        System.out.print("Enter data and time of the trip (example: '25.10.2023 14:42 Europe/Kiev'): ");
        String userInput = scanner.nextLine();
        //String userInput = "25.10.2023 14:42 Europe/Kiev";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z");
        ZonedDateTime userDateTime = ZonedDateTime.parse(userInput, inputFormatter);

        //__________________________________Transport_____________________________________
        Map<String, TransportType> transportTypes = generateTransportTypes();
        List<TransportType> availableTransport = new ArrayList<>();
        System.out.println("Available transport options:");
        for (String transportName : transportTypes.keySet()) {
            TransportType transport = transportTypes.get(transportName);
            System.out.println(transportName + " - Speed: " + transport.getSpeed()
                    + " km/h, Price per 100 km: " + transport.getPricePer100Km() + " UAH");
        }

        System.out.print("Enter the names of available transport (comma-separated, e.g., Economy Plane, Economy Train): ");
        String selectedTransport = scanner.nextLine();
        String[] transportNames = selectedTransport.split(", ");
        for (String transportName : transportNames) {
            if (transportTypes.containsKey(transportName)) {
                availableTransport.add(transportTypes.get(transportName));
            } else {
                System.out.println("Invalid transport name: " + transportName);
            }
        }

        //__________________________________Budget_____________________________________
        System.out.print("Please, write the trip budget: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline character left in the buffer


        //____________________________calculate_shortest_path____________________________

        List<City> shortestPath;

        if (isStops) {
            shortestPath = PathCalculator.dijkstraWithIntermediatePoints(cities, startCity, endCity, stops);
        } else {
            shortestPath = PathCalculator.dijkstra(cities, startCity, endCity);
        }

        if (shortestPath != null) {
            System.out.println("Shortest path from " + startCity + " to " + endCity + " with stops: " + stops);

            StringBuilder pathString = new StringBuilder();
            for (City city : shortestPath) {
                pathString.append(city.getName()).append(" -> ");
            }
            pathString.delete(pathString.length() - 4, pathString.length()); // Remove the last " -> "
            System.out.println(pathString.toString());
        } else {
            System.out.println("Unable to find a path through the stops: " + stops);
        }

        //____________________________calculate_optimal_transport____________________________
        List<TransportType> listOfTransport = calculateOptimalTransport(shortestPath, maxPrice, availableTransport);

        StringBuilder routeBuilder = new StringBuilder();
        for (TransportType transport : listOfTransport) {
            routeBuilder.append(transport.getName()).append(" -> ");
        }

        //____________________________calculate_arrival_time__________________________________
        List<ZonedDateTime> zonedDateTimes = calculateArrivalTimeForPath(shortestPath, listOfTransport, userDateTime);

        for (int i = 0; i < zonedDateTimes.size(); i++) {
            ZonedDateTime arrivalTime = zonedDateTimes.get(i);
            System.out.println("Arrival in " + shortestPath.get(i).getName() + " at " + arrivalTime);
        }

        if (routeBuilder.length() >= 4) {
            routeBuilder.delete(routeBuilder.length() - 4, routeBuilder.length());
        }
        System.out.println(routeBuilder.toString());


        //_________________________Calculate_price_and_hours_for_trip____________________________
        System.out.println("\nPrice: " + calculatePriceForWholePath(shortestPath, listOfTransport) + " UAH");
        System.out.println("Time: " + calculateTimeForWholePath(shortestPath, listOfTransport) + " Hours");
    }
}