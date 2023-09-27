package org.example;


import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class NecklacesGenerator {
    List<Pebble> preciousPebbleList = new ArrayList<>(List.of(
            new PreciousPebble("Diamond", "Carbon", 2.0, 5000.0,
                    0.95, PebbleForm.ROUND, 0.9, 2.42),
            new PreciousPebble("Ruby", "Corundum", 1.5, 3000.0,
                    0.92, PebbleForm.OVAL, 0.87, 1.77),
            new PreciousPebble("Sapphire", "Corundum", 1.8, 2800.0,
                    0.94, PebbleForm.PRINCESS, 0.86, 1.76),
            new PreciousPebble("Emerald", "Beryl", 1.3, 3200.0,
                    0.88, PebbleForm.MARQUISE, 0.85, 1.74),
            new PreciousPebble("Topaz", "Silicate", 1.7, 2400.0,
                    0.89, PebbleForm.RADIANT, 0.88, 1.78),
            new PreciousPebble("Amethyst", "Quartz", 1.2, 1800.0,
                    0.91, PebbleForm.CUSHION, 0.83, 1.70),
            new PreciousPebble("Aquamarine", "Beryl", 1.6, 2100.0,
                    0.90, PebbleForm.ASSCHER, 0.84, 1.72),
            new PreciousPebble("Garnet", "Garnet", 1.4, 1600.0,
                    0.93, PebbleForm.BAGUETTE, 0.82, 1.69),
            new PreciousPebble("Peridot", "Olivine", 1.1, 2200.0,
                    0.86, PebbleForm.TRILLION, 0.80, 1.68),
            new PreciousPebble("Tourmaline", "Borosilicate", 1.9, 1900.0,
                    0.87, PebbleForm.HEXAGONAL, 0.81, 1.71)
    ));

    List<Pebble> semiPreciousPebbleList = new ArrayList<>(List.of(
            new SemiPreciousPebble("Amber", "Organic", 1.0, 300.0, 0.85,
                    PebbleForm.ROUND, true, false, 0.75),
            new SemiPreciousPebble("Jade", "Silicate", 1.2, 400.0, 0.88,
                    PebbleForm.OVAL, false, false, 0.80),
            new SemiPreciousPebble("Lapis Lazuli", "Metamorphic", 1.5, 350.0, 0.90,
                    PebbleForm.PEAR, true, true, 0.85),
            new SemiPreciousPebble("Moonstone", "Feldspar", 1.1, 250.0, 0.87,
                    PebbleForm.HEART, false, true, 0.78),
            new SemiPreciousPebble("Citrine", "Quartz", 1.3, 320.0, 0.89,
                    PebbleForm.BAGUETTE, true, false, 0.82),
            new SemiPreciousPebble("Aventurine", "Quartz", 1.4, 280.0, 0.91,
                    PebbleForm.CUSHION, true, false, 0.79),
            new SemiPreciousPebble("Hematite", "Oxide", 1.6, 200.0, 0.86,
                    PebbleForm.ROUND, false, false, 0.72),
            new SemiPreciousPebble("Rhodonite", "Silicate", 1.2, 260.0, 0.88,
                    PebbleForm.OVAL, true, false, 0.76),
            new SemiPreciousPebble("Sodalite", "Tectosilicate", 1.7, 180.0, 0.82,
                    PebbleForm.RADIANT, false, false, 0.74),
            new SemiPreciousPebble("Turquoise", "Phosphate", 1.0, 220.0, 0.83,
                    PebbleForm.HEXAGONAL, true, false, 0.77)
    ));


    private Necklace CreateNecklace() {

        Necklace necklace = new Necklace();
        Random random = new Random();

        int numberOfPrecious = random.nextInt(10);
        int numberOfSemi = random.nextInt(10);

        for(int i = 0; i < numberOfPrecious; i++) {
            int orderyPebble = random.nextInt(10);
            necklace.addPebble(preciousPebbleList.get(orderyPebble));
        }

        for(int j = 0; j < numberOfSemi; j++) {
            int orderyPebble = random.nextInt(10);
            necklace.addPebble(semiPreciousPebbleList.get(orderyPebble));
        }

        return necklace;
    }


    public List<Necklace> GenerateCollectionOfNeckleces(int numberOfNeckleces)
    {
        List<Necklace> necklaceList = new ArrayList<>();
        for( int i = 0; i < numberOfNeckleces; i++)
        {
            necklaceList.add(CreateNecklace());
        }

        return necklaceList;
    }
}











