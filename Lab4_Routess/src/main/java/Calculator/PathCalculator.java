package Calculator;

import City.City;

import java.util.*;

public class PathCalculator {
    public static List<City> dijkstra(Map<String, City> cities, String startCity, String endCity) {
        Map<String, Integer> shortestDistances = new HashMap<>();
        Map<String, String> previousCities = new HashMap<>();
        Set<String> visitedCities = new HashSet<>();

        for (String city : cities.keySet()) {
            shortestDistances.put(city, Integer.MAX_VALUE);
        }
        shortestDistances.put(startCity, 0);

        while (!visitedCities.contains(endCity)) {
            String currentCityName = getClosestCity(shortestDistances, visitedCities);

            if (currentCityName == null) {
                break; // Якщо шлях до кінцевого міста не знайдено, завершити
            }

            visitedCities.add(currentCityName);

            City currentCity = cities.get(currentCityName);
            Map<String, Integer> neighbors = currentCity.getCityDistances();

            for (String neighbor : neighbors.keySet()) {
                int tentativeDistance = shortestDistances.get(currentCityName) + neighbors.get(neighbor);

                if (tentativeDistance < shortestDistances.get(neighbor)) {
                    shortestDistances.put(neighbor, tentativeDistance);
                    previousCities.put(neighbor, currentCityName);
                }
            }
        }

        return buildShortestPath(cities, startCity, endCity, previousCities);
    }

    public static List<City> dijkstraWithIntermediatePoints(Map<String, City> cities, String startCity, String endCity, List<String> intermediatePoints) {
        List<City> finalPath = new ArrayList<>();

        if (!intermediatePoints.isEmpty()) {
            for (int i = 0; i < intermediatePoints.size() - 1; i++) {
                List<City> path = dijkstra(cities, intermediatePoints.get(i), intermediatePoints.get(i + 1));
                finalPath.addAll(path.subList(0, path.size() - 1));
            }
        }

        List<City> pathToFinalPoint = dijkstra(cities, startCity, intermediatePoints.get(0));
        finalPath.addAll(pathToFinalPoint);

        List<City> pathToDestination = dijkstra(cities, intermediatePoints.get(intermediatePoints.size() - 1), endCity);
        finalPath.addAll(pathToDestination.subList(1, pathToDestination.size()));

        return finalPath;
    }

    private static String getClosestCity(Map<String, Integer> distances, Set<String> visitedCities) {
        int minDistance = Integer.MAX_VALUE;
        String closestCity = null;

        for (String city : distances.keySet()) {
            int distance = distances.get(city);
            if (distance < minDistance && !visitedCities.contains(city)) {
                minDistance = distance;
                closestCity = city;
            }
        }
        return closestCity;
    }

    private static List<City> buildShortestPath(Map<String, City> cities, String startCity, String endCity, Map<String, String> previousCities) {
        List<City> path = new ArrayList<>();
        String currentCityName = endCity;

        while (currentCityName != null) {
            path.add(cities.get(currentCityName));
            currentCityName = previousCities.get(currentCityName);
        }

        Collections.reverse(path); // Перевернути, щоб отримати шлях від початкового до кінцевого міста

        return path;
    }

}
