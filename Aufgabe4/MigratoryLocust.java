/**
 * Wenn MigratoryLocust imp. wird, darf kein anderes Interface imp. werden.
 */
public interface MigratoryLocust extends Insect, SwarmAnimal {

    /**
     * Calling this method increases the probability of the animal joining a swarm.
     */
    void touch();
}
