/**
 * This Object represents the Rank of an Animal.
 */
public interface Rank {
    /**
     * Returns the minutes passed between the Birthday of the Animal and the date it became Alpha
     * If this Animal is not Alpha, it will return -1
     * @return :
     *      If Animal is Alpha: Minutes between the birthday of the Animal and the date it became Alpha
     *      If Animal is not Alpha: -1
     */
    int getAlphaDate();

    /**
     * Checks whether this Rank is Alpha or not
     * @return :
     *      TRUE, if this Rank is Alpha
     *      FALSE, if this Rank is not Alpha
     */
    boolean isAlpha();
}
