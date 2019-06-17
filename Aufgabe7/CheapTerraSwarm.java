/**
 * Represents a cheap TerraSwarm which lives in a Terrarium
 */
public class CheapTerraSwarm implements TerraSwarm {
    /**
     * Name of the CheapTerraSwarm
     */
    private String name;
    /**
     * Vivarium, which the CheapTerraSwarm lives in.
     */
    private Vivarium inVivarium;

    /**
     * Constructor
     * @param name name of the CheapTerraSwarm
     */
    public CheapTerraSwarm(String name) {
        this.name = name;
        this.inVivarium = null;
    }

    /**
     * @return The name of this CheapTerraSwarm
     */
    public String getName() {
        return this.name;
    }

    /**
     * Assigns a Vivarium to the CheapTerraSwarm. Before adding a Vivarium to a CheapTerraSwarm,
     * the client must check if the Vivarium fits with the CheapTerraSwarm.
     * @param that Vivarium which should be assigned
     */
    @Override
    public void insertIntoVivarium(Vivarium that) {
        this.inVivarium = that;
    }

    /**
     * returns the Vivarium this CheapTerraSwarm is currently in.
     *
     * @return :
     * Vivarium this CheapTerraSwarm is currently in
     * OR
     * null, if this CheapTerraSwarm is currently in no Vivarium
     */
    @Override
    public Vivarium getVivarium() {
        return inVivarium;
    }

    /**
     * Deletes the assignment of the Vivarium to the CheapTerraSwarm.
     */
    @Override
    public void deleteVivarium() {
        this.inVivarium = null;
    }

    /**
     *  Determine if the Vivarium fits to the CheapTerraSwarm.
     * @param that Vivarium, which should be checked with the CheapTerraSwarm
     * @return -1 when Vivarium doesn't fit to the CheapTerraSwarm,
     *          0 when Vivarium fits to the CheapTerraSwarm,
     *          1 when Vivarium fits well to the CheapTerraSwarm,
     */
    @Override
    public int checkVivarium(Vivarium that) {
        return that.checkCheapTerraSwarm(this);
    }

    @Override
    public String toString() {
        return "Cheap Terra Swarm: " +
                "\n \tName: " + name +
                "\n \tin Vivarium: " + inVivarium.toString();
    }
}
