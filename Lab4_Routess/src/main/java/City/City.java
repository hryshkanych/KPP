package City;

import java.util.*;

public class City {
    private String name;
    private Map<String, Integer> cityDistances = new HashMap<>(); // відстані до міст
    private String timeZone;
    public City(String name, String timeZone) {
        this.name = name;
        this.timeZone = timeZone;
    }

    public String getTimeZone(){
        return timeZone;
    }
    public void addNeighbor(String neighborName, int distance) {
        cityDistances.put(neighborName, distance);
    }
    public Integer getDistanceToCity(String cityName) {
        return cityDistances.get(cityName);
    }
    public Map<String, Integer> getCityDistances() {
        return cityDistances;
    }
    public String getName() {
        return name;
    }

}