/**
 * Represents a Swarm which lives in a Terrarium
 */
public interface TerraSwarm extends Swarm{
    /**
     * @return The name of this TerraSwarm
     */
    String getName();

    /**
     * Assignes a Vivarium to the TerraSwarm. Before adding a Vivarium to a TerraSwarm,
     * the client must check if the Vivarium fits with the TerraSwarm.
     * @param that Vivarium which should be assigned
     */
    void insertIntoVivarium(Vivarium that);

    /**
     * returns the Vivarium this TerraSwarm is currently in.
     * @return :
     *      Vivarium this TerraSwarm is currently in
     *      OR
     *      null, if this TerraSwarm is currently in no Vivarium
     */
    Vivarium getVivarium();

    /**
     * Deletes the assignment of the Vivarium to the TerraSwarm.
     */
    void deleteVivarium();

    /**
     *  Determine if the Vivarium fits to the TerraSwarm.
     * @param that Vivarium, which should be checked with the TerraSwarm
     * @return -1 when Vivarium doesn't fit to the TerraSwarm,
     *          0 when Vivarium fits to the TerraSwarm,
     *          1 when Vivarium fits well to the TerraSwarm,
     */
    int checkVivarium(Vivarium that);

    String toString();
}