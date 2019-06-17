/**
 * Represents a expensive TerraSwarm which lives in ExpensiveTerrarium
 */
public class ExpensiveTerraSwarm implements TerraSwarm {
    /**
     * Name of the ExpensiveTerraSwarm
     */
    private String name;
    /**
     * Vivarium, which the ExpensiveTerraSwarm lives in.
     */
    private Vivarium inVivarium;

    /**
     * Constructor
     * @param name name of the ExpensiveTerraSwarm
     */
    public ExpensiveTerraSwarm(String name) {
        this.name = name;
        this.inVivarium = null;
    }

    /**
     * @return The name of this ExpensiveTerraSwarm
     */
    public String getName() {
        return this.name;
    }

    /**
     * Assignes a Vivarium to the ExpensiveTerraSwarm. Before adding a Vivarium to a ExpensiveTerraSwarm,
     * the client must check if the Vivarium fits with the ExpensiveTerraSwarm.
     * @param that Vivarium which should be assigned
     */
    @Override
    public void insertIntoVivarium(Vivarium that) {
        this.inVivarium = that;
    }

    /**
     * returns the Vivarium this ExpensiveTerraSwarm is currently in.
     *
     * @return :
     * Vivarium this ExpensiveTerraSwarm is currently in
     * OR
     * null, if this ExpensiveTerraSwarm is currently in no Vivarium
     */
    @Override
    public Vivarium getVivarium() {
        return inVivarium;
    }

    /**
     * Deletes the assignment of the Vivarium to the ExpensiveTerraSwarm.
     */
    @Override
    public void deleteVivarium() {
        this.inVivarium = null;
    }

    /**
     *  Determine if the Vivarium fits to the ExpensiveTerraSwarm.
     * @param that Vivarium, which should be checked with the ExpensiveTerraSwarm
     * @return -1 when Vivarium doesn't fit to the ExpensiveTerraSwarm,
     *          0 when Vivarium fits to the ExpensiveTerraSwarm,
     *          1 when Vivarium fits well to the ExpensiveTerraSwarm,
     */
    @Override
    public int checkVivarium(Vivarium that) {
        return that.checkExpensiveTerraSwarm(this);
    }

    @Override
    public String toString() {
        return "Expensive Terra Swarm: " +
                "\n \tName: " + name +
                "\n \tin Vivarium: " + inVivarium.toString();
    }
}
