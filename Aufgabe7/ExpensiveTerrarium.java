/**
 * Represents a expensive Terrarium for ExpensiveTerraSwarms or CheapTerraSwarms
 */
public class ExpensiveTerrarium implements Terrarium {
    /**
     * Dimension of the ExpensiveTerrarium
     * (must be greater than 0)
     */
    private float length;
    private float width;
    private float height;

    /**
     * Constructor
     * @param length of the ExpensiveTerrarium (must be greater than 0)
     * @param width of the ExpensiveTerrarium (must be greater than 0)
     * @param height of the ExpensiveTerrarium (must be greater than 0)
     */
    public ExpensiveTerrarium(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the volume
     * @return volume of the ExpensiveTerrarium
     */
    public float getVolume() {
        return this.length * this.width * this.height;
    }

    /**
     * @param that CheapAquaSwarm which should be check with the ExpensiveTerrarium
     * @return -1, because a CheapAquaSwarm doesn't fit in a ExpensiveTerrarium
     */
    @Override
    public int checkCheapAquaSwarm(CheapAquaSwarm that) {
        return -1;
    }

    /**
     * @param that ExpensiveAquaSwarm which should be check with the ExpensiveTerrarium
     * @return -1, because a ExpensiveAquaSwarm doesn't fit in a ExpensiveTerrarium
     */
    @Override
    public int checkExpensiveAquaSwarm(ExpensiveAquaSwarm that) {
        return -1;
    }

    /**
     * @param that CheapTerraSwarm which should be check with the ExpensiveTerrarium
     * @return 0, because a CheapTerraSwarm fits in a ExpensiveTerrarium
     */
    @Override
    public int checkCheapTerraSwarm(CheapTerraSwarm that) {
        return 0;
    }

    /**
     * @param that ExpensiveTerraSwarm which should be check with the ExpensiveTerrarium
     * @return 1, because a ExpensiveTerraSwarm fit well in a ExpensiveTerrarium
     */
    @Override
    public int checkExpensiveTerraSwarm(ExpensiveTerraSwarm that) {
        return 1;
    }

    @Override
    public String toString() {
        return "Expensive Terrarium: " +
                "\n \tLaenge: " + length +
                "\n \tBreite: " + width +
                "\n \tHoehe:  " + height + "\n";
    }
}
