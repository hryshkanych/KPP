package car;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoRia {
    private List<Car> firstCarList;
    private List<Car> secondCarList;
    private List<Car> combinedCarList;

    Map<Integer, List<Car>> firstSpeedMap;
    Map<Integer, List<Car>> secondSpeedMap;

    int whatToPrint;

    int numberOfCarsToPrint;

    public AutoRia(List<Car> firstCarList, List<Car> secondCarList) {
        this.whatToPrint = 0;
        this.numberOfCarsToPrint = 0;
        this.firstCarList = firstCarList;
        this.secondCarList = secondCarList;

        this.combinedCarList = new ArrayList<>(firstCarList);
        combinedCarList.addAll(secondCarList);

        // Ініціалізація об'єктів firstSpeedMap і secondSpeedMap
        this.firstSpeedMap = new HashMap<>();
        this.secondSpeedMap = new HashMap<>();

        // Далі ваш код для заповнення firstSpeedMap і secondSpeedMap
        for (Car car : firstCarList) {
            int maxSpeed = car.getMaxSpeed();
            if (!firstSpeedMap.containsKey(maxSpeed)) {
                firstSpeedMap.put(maxSpeed, new ArrayList<>());
            }
            firstSpeedMap.get(maxSpeed).add(car);
        }

        for (Car car : secondCarList) {
            int maxSpeed = car.getMaxSpeed();
            if (!secondSpeedMap.containsKey(maxSpeed)) {
                secondSpeedMap.put(maxSpeed, new ArrayList<>());
            }
            secondSpeedMap.get(maxSpeed).add(car);
        }
    }


    public void SetWhatToPrint(int whatToPrint) {
        this.whatToPrint = whatToPrint;
    }

    public void SetNumberOfCarsToPrint(int number) {
        this.numberOfCarsToPrint = number;
    }

    public List<Car> getFirstCarList() {
        return firstCarList;
    }

    public void setFirstCarList(List<Car> firstCarList) {
        this.firstCarList = firstCarList;
    }

    public List<Car> getSecondCarList() {
        return secondCarList;
    }

    public void setSecondCarList(List<Car> secondCarList) {
        this.secondCarList = secondCarList;
    }

    public Map<Integer, List<Car>> getFirstSpeedMap() { return firstSpeedMap; }

    public Map<Integer, List<Car>> getSecondSpeedMap() { return secondSpeedMap; }

    public List<Car> getCombinedCarList() {
        return combinedCarList;
    }


    public String toString() {
        StringBuilder fullAutoInfo = new StringBuilder();

        if (whatToPrint == 0) {
            appendCarList(fullAutoInfo, "________________________First Car List________________________", firstCarList);
            appendCarList(fullAutoInfo, "________________________Second Car List________________________", secondCarList);
        } else if (whatToPrint == 1) {
            appendCarMap(fullAutoInfo, "________________________First Car Map________________________", firstSpeedMap);
            appendCarMap(fullAutoInfo, "________________________Second Car Map________________________", secondSpeedMap);
        } else {
            appendCarList(fullAutoInfo, "________________________Combined Car List________________________", combinedCarList);
        }

        return fullAutoInfo.toString();
    }

    private void appendCarList(StringBuilder builder, String listName, List<Car> carList) {
        builder.append(listName).append("\n");

        for (Car car : carList) {
            builder.append(car).append("\n");
        }
    }

    private void appendCarMap(StringBuilder builder, String listName, Map<Integer, List<Car>> speedMap) {
        builder.append(listName).append("\n");

        for (Map.Entry<Integer, List<Car>> entry : speedMap.entrySet()) {
            int maxSpeed = entry.getKey();
            List<Car> carList = entry.getValue();

            builder.append("Max speed: ").append(maxSpeed).append("\n");

            int n = Math.min(carList.size(), numberOfCarsToPrint);
            for (int i = 0; i < n; i++) {
                builder.append("  ").append(carList.get(i)).append("\n");
            }
        }
    }
}
