/**
 * Represents a cheap AquaSwarm which lives in Aquarium
 */
public class CheapAquaSwarm implements AquaSwarm {
    /**
     * Name of the CheapAquaSwarm
     */
    private String name;
    /**
     * Vivarium, which the CheapAquaSwarm lives in.
     */
    private Vivarium inVivarium;

    /**
     * Constructor
     * @param name name of the CheapAquaSwarm
     */
    public CheapAquaSwarm(String name) {
        this.name = name;
        this.inVivarium = null;
    }

    /**
     * @return The name of this CheapAquaSwarm
     */
    public String getName() {
        return this.name;
    }


    /**
     * Assignes a Vivarium to the CheapAquaSwarm. Before adding a Vivarium to a CheapAquaSwarm,
     * the client must check if the Vivarium fits with the CheapAquaSwarm.
     * @param that Vivarium which should be assigned
     */
    @Override
    public void insertIntoVivarium(Vivarium that) {
        this.inVivarium = that;
    }

    /**
     * returns the Vivarium this CheapAquaSwarm is currently in.
     *
     * @return :
     * Vivarium this CheapAquaSwarm is currently in
     * OR
     * null, if this CheapAquaSwarm is currently in no Vivarium
     */
    @Override
    public Vivarium getVivarium() {
        return inVivarium;
    }

    /**
     * Deletes the assignment of the Vivarium to the CheapAquaSwarm.
     */
    @Override
    public void deleteVivarium() {
        this.inVivarium = null;
    }

    /**
     *  Determine if the Vivarium fits to the CheapAquaSwarm.
     * @param that Vivarium, which should be checked with the CheapAquaSwarm
     * @return -1 when Vivarium doesn't fit to the CheapAquaSwarm,
     *          0 when Vivarium fits to the CheapAquaSwarm,
     *          1 when Vivarium fits well to the CheapAquaSwarm,
     */
    @Override
    public int checkVivarium(Vivarium that) {
        return that.checkCheapAquaSwarm(this);
    }

    @Override
    public String toString() {
        return "Cheap Aqua Swarm: " +
                "\n \tName: " + name +
                "\n \tin Vivarium: " + inVivarium.toString();
    }
}
