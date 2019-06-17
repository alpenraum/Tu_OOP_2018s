/**
 * Wenn HerdAnimal imp. wird muss entweder ein Mammal oder ein FlightlessBird auch imp. werden.
 */

public interface HerdAnimal extends SocialAnimal{
    /**
     * This method returns a reference of the alpha, when the animal has one.
     * Is the alpha not in the herd or the herd has not alpha it will returns "null".
     * @return reference of the alpha, or "null" if it has no alpha in its herd
     */
    Animal getAlpha();

    /**
     * Sets the new alpha of the animal.
     * @param newAlpha reference of the new alpha
     */
    void setAlpha(Animal newAlpha);

    /**
     * After calling this method, the animal is no longer in a herd.
     */
    void leave();
}
