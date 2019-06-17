/**
 * Represents a cheap Aquarium for CheapTerraSwarms
 */
public class CheapAquarium implements Aquarium{
    /**
     * Dimension of the CheapAquarium
     * (must be greater than 0)
     */
    private float length;
    private float width;
    private float height;

    /**
     * Constructor
     * @param length of the CheapAquarium (must be greater than 0)
     * @param width of the CheapAquarium (must be greater than 0)
     * @param height of the CheapAquarium (must be greater than 0)
     */
    public CheapAquarium(float length, float width, float height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the volume
     * @return volume of the CheapAquarium
     */
    public float getVolume(){
        return this.length * this.width * this.height;
    }

    /**
     * @param that CheapAquaSwarm which should be check with the CheapAquarium
     * @return 1, because a CheapAquaSwarm fits well in a CheapAquarium
     */
    @Override
    public int checkCheapAquaSwarm(CheapAquaSwarm that) { return 1; }

    /**
     * @param that ExpensiveAquaSwarm which should be check with the CheapAquarium
     * @return -1, because a ExpensiveAquaSwarm doesn't fit in a CheapAquarium
     */
    @Override
    public int checkExpensiveAquaSwarm(ExpensiveAquaSwarm that) {
        return -1;
    }

    /**
     * @param that CheapTerraSwarm which should be check with the CheapAquarium
     * @return -1, because a CheapTerraSwarm doesn't fit in a CheapAquarium
     */
    @Override
    public int checkCheapTerraSwarm(CheapTerraSwarm that) { return -1; }

    /**
     * @param that ExpensiveTerraSwarm which should be check with the CheapAquarium
     * @return -1, because a ExpensiveTerraSwarm doesn't fit in a CheapAquarium
     */
    @Override
    public int checkExpensiveTerraSwarm(ExpensiveTerraSwarm that) {
        return -1;
    }

    @Override
    public String toString() {
        return "Cheap Aquarium: " +
                "\n \tLaenge: "+length +
                "\n \tBreite: "+width +
                "\n \tHoehe:  "+height+"\n";
    }

}
