package Services;

import PizzeriaComponents.Pizzeria;

import java.io.*;

public class SerializationService {

    // Метод для серіалізації об'єкта Pizzeria
    public static void serializePizzeria(Pizzeria pizzeria, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pizzeria);
        } catch (IOException e) {
            System.err.println("Serialization error: " + e.getMessage());
        }
    }

    // Метод для десеріалізації об'єкта Pizzeria
    public static Pizzeria deserializePizzeria(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Pizzeria) ois.readObject();
        } catch (IOException e) {
            System.err.println("Deserialization IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Deserialization class not found error: " + e.getMessage());
        }
        return null;
    }
}
