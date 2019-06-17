/**
 * Animal darf selbst nicht direkt von einer Klasse imp. werden.
 * Stattdessen muss ein Tier einer Spezies(Animal, Bird, Insect oder Mammal)
 * oder nur ein Untertyp einer Spezies implementiert werden.
 */

public interface Animal {
    /**
     *
     * @return Expected time how long this Animal is in the air
     */
     int air();

    /**
     *
     * @return Expected time how long this Animal is in the water
     */
     int water();

    /**
     *
     * @return Expected time how long this Animal is on the ground
     */
     int ground();
}
