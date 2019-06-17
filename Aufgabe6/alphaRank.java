/**
 * This Object represents the Alpha-Rank of an Animal.
 */
public class alphaRank implements Rank {
    /**
     * Saves the minutes the minutes passed between the Birthday of the Animal and the date it became Alpha
     */
   private int alphaDate;


    public alphaRank(int date){
        this.alphaDate=date;
    }

    /**
     * Returns the minutes passed between the Birthday of the Animal and the date it became Alpha
     * If this Animal is not Alpha, it will return -1
     *
     * @return Minutes between the birthday of the Animal and the date it became Alpha
     */
    @Override
    public int getAlphaDate() {
        return alphaDate;
    }

    /**
     * Checks whether this Rank is Alpha or not
     * @return TRUE
     *

     */
    public boolean isAlpha(){
        return true;
    }
}
