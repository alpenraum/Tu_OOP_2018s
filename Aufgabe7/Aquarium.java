/**
 * Represents a Aquarium for AquaSwarms.
 */
public interface Aquarium extends Vivarium {
    /**
     * Calculates the volume of the Aquarium.
     * @return volume of the Aquarium
     */
    float getVolume();

    /**
     * Determine if the CheapAquaSwarm fits in the Aquarium.
     * @param that CheapAquaSwarm which should be check with the Aquarium
     * @return -1 when CheapAquaSwarm doesn't fit in the Aquarium,
     *          0 when CheapAquaSwarm fits in the Aquarium,
     *          1 when CheapAquaSwarm fits well in the Aquarium
     */
    int checkCheapAquaSwarm(CheapAquaSwarm that);

    /**
     * Determine if the ExpensiveAquaSwarm fits in the Aquarium.
     * @param that ExpensiveAquaSwarm which should be check with the Aquarium
     * @return -1 when ExpensiveAquaSwarm doesn't fit in the Aquarium,
     *          0 when ExpensiveAquaSwarm fits in the Aquarium,
     *          1 when ExpensiveAquaSwarm fits well in the Aquarium
     */
    int checkExpensiveAquaSwarm(ExpensiveAquaSwarm that);

    /**
     * Determine if the CheapTerraSwarm fits in the Aquarium.
     * @param that CheapTerraSwarm which should be check with the Aquarium
     * @return -1 when CheapTerraSwarm doesn't fit in the Aquarium,
     *          0 when CheapTerraSwarm fits in the Aquarium,
     *          1 when CheapTerraSwarm fits well in the Aquarium
     */
    int checkCheapTerraSwarm(CheapTerraSwarm that);

    /**
     * Determine if the ExpensiveTerraSwarm fits in the Aquarium.
     * @param that ExpensiveTerraSwarm which should be check with the Aquarium
     * @return -1 when ExpensiveTerraSwarm doesn't fit in the Aquarium,
     *          0 when ExpensiveTerraSwarm fits in the Aquarium,
     *          1 when ExpensiveTerraSwarm fits well in the Aquarium
     */
    int checkExpensiveTerraSwarm(ExpensiveTerraSwarm that);

    String toString();
}
