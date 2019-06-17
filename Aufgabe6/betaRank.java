/**
 * This Object represents the Beta-Rank of an Animal.
 */
public class betaRank implements Rank {

    public betaRank(){}
    /**
     * Returns the minutes passed between the Birthday of the Animal and the date it became Alpha
     * If this Animal is not Alpha, it will return -1
     *
     * @return -1
     */
    @Override
    public int getAlphaDate() {
        return -1;
    }
    /**
     * Checks whether this Rank is Alpha or not
     * @return FALSE
     */
    public boolean isAlpha(){
        return false;
    }
}
