package City;

import City.City;

import java.util.HashMap;
import java.util.Map;

public class CityGenerator {

    public static  Map<String, City> GenerateCities() {
        Map<String, City> cities = new HashMap<>();

        City cityKyiv = new City("Kyiv", "Europe/Kiev");
        City cityWarshaw = new City("Warshaw", "Europe/Warsaw");
        City cityBudapest = new City("Budapest", "Europe/Budapest");
        City cityBratislava = new City("Bratislava", "Europe/Bratislava");
        City cityViden = new City("Viden", "Europe/Vienna");
        City cityPraga = new City("Praga", "Europe/Prague");
        City cityBerlin = new City("Berlin", "Europe/Berlin");
        City cityBarselona = new City("Barselona", "Europe/Madrid");
        City cityParis = new City("Paris", "Europe/Paris");

        //Kyiv
        cityKyiv.addNeighbor("Warshaw", 787);
        cityKyiv.addNeighbor("Bratislava", 1336);
        cityKyiv.addNeighbor("Budapest", 1118);
        //Warshaw
        cityWarshaw.addNeighbor("Kyiv", 787);
        cityWarshaw.addNeighbor("Berlin", 572);
        cityWarshaw.addNeighbor("Praga", 483);
        cityWarshaw.addNeighbor("Bratislava", 662);
        //Bratislava
        cityBratislava.addNeighbor("Kyiv", 1336);
        cityBratislava.addNeighbor("Warshaw", 662);
        cityBratislava.addNeighbor("Budapest", 200);
        cityBratislava.addNeighbor("Viden", 79);
        //Berlin
        cityBerlin.addNeighbor("Warshaw", 572);
        cityBerlin.addNeighbor("Praga", 280);
        cityBerlin.addNeighbor("Paris", 883);
        //Paris
        cityParis.addNeighbor("Berlin", 883);
        cityParis.addNeighbor("Barselona", 1038);
        cityParis.addNeighbor("Praga", 873);
        //Barselona
        cityBarselona.addNeighbor("Paris", 1038);
        cityBarselona.addNeighbor("Viden", 1799);
        //Viden
        cityViden.addNeighbor("Barselona", 1799);
        cityViden.addNeighbor("Praga", 333);
        cityViden.addNeighbor("Bratislava", 79);
        cityViden.addNeighbor("Budapest", 248);
        //Praga
        cityPraga.addNeighbor("Paris", 873);
        cityPraga.addNeighbor("Berlin", 280);
        cityPraga.addNeighbor("Warshaw", 483);
        cityPraga.addNeighbor("Viden", 333);
        //Budapest
        cityBudapest.addNeighbor("Viden", 248);
        cityBudapest.addNeighbor("Bratislava", 200);
        cityBudapest.addNeighbor("Kyiv", 1118);


        cities.put("Kyiv", cityKyiv);
        cities.put("Warshaw", cityWarshaw);
        cities.put("Bratislava", cityBratislava);
        cities.put("Budapest", cityBudapest);
        cities.put("Praga", cityPraga);
        cities.put("Berlin", cityBerlin);
        cities.put("Viden", cityViden);
        cities.put("Paris", cityParis);
        cities.put("Barselona", cityBarselona);

        return cities;
    }

}
