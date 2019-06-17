/**
 * Represents a Vivarium for Swarms.
 */
public interface Vivarium {
    /**
     * Calculates the volume of the vivarium.
     * @return volume of the vivarium
     */
     float getVolume();

    /**
     * Determine if the CheapAquaSwarm fits in the Vivarium.
     * @param that CheapAquaSwarm which should be checked with the Vivarium
     * @return -1 if CheapAquaSwarm doesn't fit in the Vivarium,
     *          0 if CheapAquaSwarm fits in the Vivarium,
     *          1 if CheapAquaSwarm fits well in the Vivarium
     */
    int checkCheapAquaSwarm(CheapAquaSwarm that);

    /**
     * Determine if the ExpensiveAquaSwarm fits in the Vivarium.
     * @param that ExpensiveAquaSwarm which should be checked with the Vivarium
     * @return -1 if ExpensiveAquaSwarm doesn't fit in the Vivarium,
     *          0 if ExpensiveAquaSwarm fits in the Vivarium,
     *          1 if ExpensiveAquaSwarm fits well in the Vivarium
     */
    int checkExpensiveAquaSwarm(ExpensiveAquaSwarm that);

    /**
     * Determine if the CheapTerraSwarm fits in the Vivarium.
     * @param that CheapTerraSwarm which should be checked with the Vivarium
     * @return -1 if CheapTerraSwarm doesn't fit in the Vivarium,
     *          0 if CheapTerraSwarm fits in the Vivarium,
     *          1 if CheapTerraSwarm fits well in the Vivarium
     */
    int checkCheapTerraSwarm(CheapTerraSwarm that);

    /**
     * Determine if the ExpensiveTerraSwarm fits in the Vivarium.
     * @param that ExpensiveTerraSwarm which should be checked with the Vivarium
     * @return -1 if ExpensiveTerraSwarm doesn't fit in the Vivarium,
     *          0 if ExpensiveTerraSwarm fits in the Vivarium,
     *          1 if ExpensiveTerraSwarm fits well in the Vivarium
     */
    int checkExpensiveTerraSwarm(ExpensiveTerraSwarm that);

    String toString();
}
