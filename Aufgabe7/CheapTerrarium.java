/**
 * Represents a cheap Terrarium for CheapTerraSwarms
 */
public class CheapTerrarium implements Terrarium {
    /**
     * Dimension of the CheapTerrarium.
     * Must be greater than 0
     */
    private float length;
    private float width;
    private float height;

    /**
     * Constructor
     * @param length of the CheapTerrarium (must be greater than 0)
     * @param width of the CheapTerrarium (must be greater than 0)
     * @param height of the CheapTerrarium (must be greater than 0)
     */
    public CheapTerrarium(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the volume
     * @return volume of the CheapTerrarium
     */
    public float getVolume() {
        return this.length * this.width * this.height;
    }

    /**
     * @param that CheapAquaSwarm which should be check with the CheapTerrarium
     * @return -1, because a CheapAquaSwarm doesn't fit in a CheapTerrarium
     */
    @Override
    public int checkCheapAquaSwarm(CheapAquaSwarm that) {
        return -1;
    }

    /**
     *
     * @param that ExpensiveAquaSwarm which should be check with the CheapTerrarium
     * @return -1, because a ExpensiveAquaSwarm doesn't fit in a CheapTerrarium
     */
    @Override
    public int checkExpensiveAquaSwarm(ExpensiveAquaSwarm that) {
        return -1;
    }

    /**
     * @param that CheapTerraSwarm which should be check with the CheapTerrarium
     * @return 1, because a CheapTerraSwarm fits well in a CheapTerrarium
     */
    @Override
    public int checkCheapTerraSwarm(CheapTerraSwarm that) {
        return 1;
    }

    /**
     * @param that ExpensiveTerraSwarm which should be check with the CheapTerrarium
     * @return -1, because a ExpensiveTerraSwarm doesn't fit in a CheapTerrarium
     */
    @Override
    public int checkExpensiveTerraSwarm(ExpensiveTerraSwarm that) {
        return -1;
    }

    @Override
    public String toString() {
        return "Cheap Terrarium: " +
                "\n \tLaenge: " + length +
                "\n \tBreite: " + width +
                "\n \tHoehe:  " + height + "\n";
    }
}
