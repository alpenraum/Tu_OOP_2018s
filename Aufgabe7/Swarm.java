/**
 * Represents a Versuchstierschwarm
 */
public interface Swarm {
    /**
     * @return The name of this Swarm
     */
    String getName();

    /**
     * Assigns a Vivarium to the Swarm.
     * Before adding a Vivarium to a Swarm, the client must check if the Vivarium fits with the Swarm.
     * @param that Vivarium which should be assigned
     */
    void insertIntoVivarium(Vivarium that);

    /**
     * returns the Vivarium this Swarm is currently in.
            * @return :
            *      Vivarium this swarm is currently in
     *      OR
     *      null, if this Swarm is currently in no Vivarium
     */
    Vivarium getVivarium();

    /**
     * Deletes the assignment of the Vivarium to the Swarm.
     */
    void deleteVivarium();

    /**
     *  Determines if the Vivarium fits to the Swarm.
     * @param that Vivarium, which should be checked with the Swarm
     * @return -1 when Vivarium doesn't fit to the Swarm,
     *          0 when Vivarium fits to the Swarm,
     *          1 when Vivarium fits well to the Swarm,
     */
    int checkVivarium(Vivarium that);

    String toString();
}
