package Calculator;

import City.City;
import Transport.TransportType;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeCalculator {
    public static double calculateTime(int kilometers, double kilometersPerHour) {
        return kilometers / kilometersPerHour;
    }

    public static double calculateTimeForWholePath(List<City> pathCities, List<TransportType> transportSequence) {
        double totalTime = 0;
        int i = 0;
        for(TransportType transport : transportSequence) {
            int distance = pathCities.get(i).getDistanceToCity(pathCities.get(i+1).getName());
            totalTime += calculateTime(distance, transport.getSpeed());
            i++;
        }
        return totalTime;
    }

    public static List<ZonedDateTime> calculateArrivalTimeForPath(List<City> pathCities, List<TransportType> transportSequence, ZonedDateTime userDateTime) {
        List<ZonedDateTime> arrivalTimes = new ArrayList<>();
        ZonedDateTime currentDateTime = userDateTime;

        arrivalTimes.add(currentDateTime);

        for (int i = 1; i < pathCities.size(); i++) {
            City fromCity = pathCities.get(i - 1);
            City toCity = pathCities.get(i);
            double distance = fromCity.getCityDistances().get(toCity.getName());


            TransportType currentTransport = transportSequence.get(i - 1);


            double travelTime = distance / currentTransport.getSpeed();  // поточний відрізок


            currentDateTime = currentDateTime.plusMinutes(Math.round(travelTime * 60));


            ZoneId cityTimeZone = ZoneId.of(toCity.getTimeZone());
            currentDateTime = currentDateTime.withZoneSameInstant(cityTimeZone); //застосування часового поясу

            arrivalTimes.add(currentDateTime);
        }

        return arrivalTimes;
    }

}
