package services;
import car.Car;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
public class FileReaderService {
    public static List<Car> readCarListFromFile(String fileName) {
        List<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String model = parts[0].trim();
                    int cost = Integer.parseInt(parts[1].trim());
                    int maxSpeed = Integer.parseInt(parts[2].trim());
                    cars.add(new Car(model, cost, maxSpeed));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }
}
