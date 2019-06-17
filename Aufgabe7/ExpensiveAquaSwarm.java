/**
 * Represents a expensive AquaSwarm which lives in ExpensiveAquarium
 */
public class ExpensiveAquaSwarm implements AquaSwarm {
    /**
     * Name of the ExpensiveAquaSwarm
     */
    private String name;
    /**
     * Vivarium, which the ExpensiveAquaSwarm lives in.
     */
    private Vivarium inVivarium;

    /**
     * Constructor
     * @param name name of the ExpensiveAquaSwarm
     */
    public ExpensiveAquaSwarm(String name) {
        this.name = name;
        this.inVivarium = null;
    }

    /**
     * @return The name of this ExpensiveAquaSwarm
     */
    public String getName() {
        return this.name;
    }

    /**
     * Assignes a Vivarium to the ExpensiveAquaSwarm. Before adding a Vivarium to a ExpensiveAquaSwarm,
     * the client must check if the Vivarium fits with the ExpensiveAquaSwarm.
     * @param that Vivarium which should be assigned
     */
    @Override
    public void insertIntoVivarium(Vivarium that) {
        this.inVivarium = that;
    }

    /**
     * returns the Vivarium this ExpensiveAquaSwarm is currently in.
     *
     * @return :
     * Vivarium this ExpensiveAquaSwarm is currently in
     * OR
     * null, if this ExpensiveAquaSwarm is currently in no Vivarium
     */
    @Override
    public Vivarium getVivarium() {
        return inVivarium;
    }

    /**
     * Deletes the assignment of the Vivarium to the ExpensiveAquaSwarm.
     */
    @Override
    public void deleteVivarium() {
        this.inVivarium = null;
    }

    /**
     *  Determine if the Vivarium fits to the ExpensiveAquaSwarm.
     * @param that Vivarium, which should be checked with the ExpensiveAquaSwarm
     * @return -1 when Vivarium doesn't fit to the ExpensiveAquaSwarm,
     *          0 when Vivarium fits to the ExpensiveAquaSwarm,
     *          1 when Vivarium fits well to the ExpensiveAquaSwarm,
     */
    @Override
    public int checkVivarium(Vivarium that) {
        return that.checkExpensiveAquaSwarm(this);
    }

    @Override
    public String toString() {
        return "Expensive Aqua Swarm: " +
                "\n \tName: " + name +
                "\n \tin Vivarium: " + inVivarium.toString();
    }
}
