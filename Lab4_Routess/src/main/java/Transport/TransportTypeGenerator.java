package Transport;

import Transport.TransportType;

import java.util.HashMap;
import java.util.Map;

public class TransportTypeGenerator {

    public static Map<String, TransportType> generateTransportTypes() {
        Map<String, TransportType> transportTypes = new HashMap<>();

        transportTypes.put("Economy Car", new TransportType("Economy Car", 70.0, 50.0));
        transportTypes.put("Business Car", new TransportType("Business Car", 90.0, 100.0));
        transportTypes.put("First Class Car", new TransportType("First Class Car", 130.0, 150.0));

        transportTypes.put("Economy Train", new TransportType("Economy Train", 150.0, 40.0));
        transportTypes.put("Business Train", new TransportType("Business Train", 200.0, 70.0));
        transportTypes.put("First Class Train", new TransportType("First Class Train", 250.0, 100.0));

        transportTypes.put("Economy Plane", new TransportType("Economy Plane", 700.0, 200.0));
        transportTypes.put("Business Plane", new TransportType("Business Plane", 800.0, 300.0));
        transportTypes.put("First Class Plane", new TransportType("First Class Plane", 1000.0, 400.0));

        return transportTypes;
    }
}
