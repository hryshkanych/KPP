package Calculator;


import City.City;
import Transport.TransportType;

import java.util.List;

public class PriceCalculator {
    private static double calculatePrice(int distance, double pricePerHundred){
        double pricePerOne = pricePerHundred/100;
        return pricePerOne*distance;
    }

    public static double calculatePriceForWholePath(List<City> pathCities, List<TransportType> transportSequence) {
        double totalPrice = 0;
        int i = 0;
        for(TransportType transport : transportSequence) {
            int distance = pathCities.get(i).getDistanceToCity(pathCities.get(i+1).getName());
            totalPrice += calculatePrice(distance, transport.getPricePer100Km());
            i++;
        }
        return totalPrice;
    }
}
