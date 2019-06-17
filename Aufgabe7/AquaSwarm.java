/**
 * Represents a Swarm which lives in a Aquarium
 */
public interface AquaSwarm extends Swarm{
    /**
     * @return The name of this AquaSwarm
     */
    String getName();

    /**
     * Assignes a Vivarium to the AquaSwarm. Before adding a Vivarium to a AquaSwarm,
     * the client must check if the Vivarium fits with the AquaSwarm.
     * @param that Vivarium which should be assigned
     */
    void insertIntoVivarium(Vivarium that);

    /**
     * returns the Vivarium this AquaSwarm is currently in.
     * @return :
     *      Vivarium this AquaSwarm is currently in
     *      OR
     *      null, if this AquaSwarm is currently in no Vivarium
     */
    Vivarium getVivarium();

    /**
     * Deletes the assignment of the Vivarium to the AquaSwarm.
     */
    void deleteVivarium();

    /**
     *  Determine if the Vivarium fits to the AquaSwarm.
     * @param that Vivarium, which should be checked with the AquaSwarm
     * @return -1 when Vivarium doesn't fit to the AquaSwarm,
     *          0 when Vivarium fits to the AquaSwarm,
     *          1 when Vivarium fits well to the AquaSwarm,
     */
    int checkVivarium(Vivarium that);

    String toString();
}
