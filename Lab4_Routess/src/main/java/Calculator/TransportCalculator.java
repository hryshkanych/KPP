package Calculator;

import City.City;
import Transport.TransportType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TransportCalculator {
    public static class RouteOption {
        List<TransportType> transportSequence;
        double totalCost;
        double totalTime;

        public RouteOption(List<TransportType> transportSequence, double totalCost, double totalTime) {
            this.transportSequence = transportSequence;
            this.totalCost = totalCost;
            this.totalTime = totalTime;
        }
    }

    public static List<TransportType> calculateOptimalTransport(List<City> pathCities, double maxPrice, List<TransportType> possibleTransport) {
        List<RouteOption> bestRouteOptions = new ArrayList<>();
        generateRouteOptions(pathCities, 0, possibleTransport, maxPrice, new ArrayList<>(), 0.0, 0.0, bestRouteOptions);

        if (bestRouteOptions.isEmpty()) {
            throw new IllegalArgumentException("Неможливо знайти маршрут для заданого бюджету.");
        }

        // Вибрати найкращий маршрут за часом подорожі
        Collections.sort(bestRouteOptions, (a, b) -> Double.compare(a.totalTime, b.totalTime));

        return bestRouteOptions.get(0).transportSequence;
    }

    private static void generateRouteOptions(List<City> pathCities, int currentIndex, List<TransportType> possibleTransport, double maxPrice, List<TransportType> currentSequence, double currentCost, double currentTime, List<RouteOption> bestRouteOptions) {
        if (currentIndex == pathCities.size() - 1) {
            // Досягнули кінця маршруту, перевірте вартість та час
            if (currentCost <= maxPrice) {
                bestRouteOptions.add(new RouteOption(new ArrayList<>(currentSequence), currentCost, currentTime));
            }
        } else {
            City fromCity = pathCities.get(currentIndex);
            City toCity = pathCities.get(currentIndex + 1);
            double distance = fromCity.getCityDistances().get(toCity.getName());

            for (TransportType transport : possibleTransport) {
                double costFor100Km = transport.getPricePer100Km();
                double costForSegment = (distance / 100.0) * costFor100Km;
                double timeForSegment = distance / transport.getSpeed();

                if (currentCost + costForSegment <= maxPrice) {
                    currentSequence.add(transport);
                    generateRouteOptions(pathCities, currentIndex + 1, possibleTransport, maxPrice, currentSequence, currentCost + costForSegment, currentTime + timeForSegment, bestRouteOptions);
                    currentSequence.remove(currentSequence.size() - 1);
                }
            }
        }
    }
}
