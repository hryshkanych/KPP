package car;

import java.util.*;

public class AutoRiaManager {
    public void deleteCertainCars(String[] modelArray, Map<Integer, List<Car>> firstSpeedMap,
                                  Map<Integer, List<Car>> secondSpeedMap ) {
        deleteCarsFromMap(modelArray, firstSpeedMap);
        deleteCarsFromMap(modelArray, secondSpeedMap);
    }

    private void deleteCarsFromMap(String[] modelArray, Map<Integer, List<Car>> speedMap) {
        List<Integer> keysToRemove = new ArrayList<>();

        for (Integer maxSpeed : speedMap.keySet()) {
            List<Car> carList = speedMap.get(maxSpeed);
            carList.removeIf(car -> Arrays.asList(modelArray).contains(car.getModel()));

            if (carList.isEmpty()) {
                keysToRemove.add(maxSpeed);
            }
        }

        for (Integer key : keysToRemove) {
            speedMap.remove(key);
        }
    }

    public void SortCombinedList(List<Car> combinedCarList) {
        Collections.sort(combinedCarList);
    }

    public void findCarsInPriceRange(int minPrice, int maxPrice, List<Car> combinedCarList) {
        int count = 0;

        for (Car car : combinedCarList) {
            int carPrice = car.getCost();
            if (carPrice >= minPrice && carPrice <= maxPrice) {
                count++;
            }
        }

        System.out.println("Number of cars in this diapason: "+count);
    }
}
