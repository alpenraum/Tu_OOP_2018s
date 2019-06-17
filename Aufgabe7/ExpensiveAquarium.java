/**
 * Represents a expensive Aquarium for ExpensiveAquaSwarms or CheapAquaSwarms
 */
public class ExpensiveAquarium implements Aquarium {
    /**
     * Dimensions of the ExpensiveAquarium
     *  (must be greater than 0)
     */
    private float length;
    private float width;
    private float height;

    /**
     * Creates a new ExpensiveAquarium
     * @param length length of this ExpensiveAquarium (must be greater than 0)
     * @param width width of this ExpensiveAquarium (must be greater than 0)
     * @param height height of this ExpensiveAquarium (must be greater than 0)
     */
    public ExpensiveAquarium(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the volume
     * @return volume of the ExpensiveAquarium
     */
    public float getVolume() {
        return this.length * this.width * this.height;
    }

    /**
     * @param that CheapAquaSwarm which should be checked with the ExpensiveAquarium
     * @return 0, because a CheapAquaSwarm fits in a ExpensiveAquarium
     */
    @Override
    public int checkCheapAquaSwarm(CheapAquaSwarm that) {
        return 0;
    }

    /**
     * @param that ExpensiveAquaSwarm which should be checked with the ExpensiveAquarium
     * @return 1, because a ExpensiveAquaSwarm fits well in a ExpensiveAquarium
     */
    @Override
    public int checkExpensiveAquaSwarm(ExpensiveAquaSwarm that) {
        return 1;
    }

    /**
     * @param that CheapTerraSwarm which should be checked with the ExpensiveAquarium
     * @return -1, because a CheapTerraSwarm doesn't fit in a ExpensiveAquarium
     */
    @Override
    public int checkCheapTerraSwarm(CheapTerraSwarm that) {
        return -1;
    }

    /**
     * @param that ExpensiveTerraSwarm which should be checked with the ExpensiveAquarium
     * @return -1, because a ExpensiveTerraSwarm doesn't fit in a ExpensiveAquarium
     */
    @Override
    public int checkExpensiveTerraSwarm(ExpensiveTerraSwarm that) {
        return -1;
    }

    @Override
    public String toString() {
        return "Expensive Aquarium: " +
                "\n \tLaenge: "+length +
                "\n \tBreite: "+width +
                "\n \tHoehe:  "+height+"\n";
    }
}
