/**
 * Represents a Terrarium for TerraSwarms.
 */
public interface Terrarium extends Vivarium {
    /**
     * Calculates the volume of the Terrarium.
     * @return volume of the Terrarium
     */
    float getVolume();

    /**
     * Determine if the CheapAquaSwarm fits in the Terrarium.
     * @param that CheapAquaSwarm which should be check with the Terrarium
     * @return -1 when CheapAquaSwarm doesn't fit in the Terrarium,
     *          0 when CheapAquaSwarm fits in the Terrarium,
     *          1 when CheapAquaSwarm fits well in the Terrarium
     */
    int checkCheapAquaSwarm(CheapAquaSwarm that);

    /**
     * Determine if the ExpensiveAquaSwarm fits in the Terrarium.
     * @param that ExpensiveAquaSwarm which should be check with the Terrarium
     * @return -1 when ExpensiveAquaSwarm doesn't fit in the Terrarium,
     *          0 when ExpensiveAquaSwarm fits in the Terrarium,
     *          1 when ExpensiveAquaSwarm fits well in the Terrarium
     */
    int checkExpensiveAquaSwarm(ExpensiveAquaSwarm that);

    /**
     * Determine if the CheapTerraSwarm fits in the Terrarium.
     * @param that CheapTerraSwarm which should be check with the Terrarium
     * @return -1 when CheapTerraSwarm doesn't fit in the Terrarium,
     *          0 when CheapTerraSwarm fits in the Terrarium,
     *          1 when CheapTerraSwarm fits well in the Terrarium
     */
    int checkCheapTerraSwarm(CheapTerraSwarm that);

    /**
     * Determine if the ExpensiveTerraSwarm fits in the Terrarium.
     * @param that ExpensiveTerraSwarm which should be check with the Terrarium
     * @return -1 when ExpensiveTerraSwarm doesn't fit in the Terrarium,
     *          0 when ExpensiveTerraSwarm fits in the Terrarium,
     *          1 when ExpensiveTerraSwarm fits well in the Terrarium
     */
    int checkExpensiveTerraSwarm(ExpensiveTerraSwarm that);

    String toString();
}
