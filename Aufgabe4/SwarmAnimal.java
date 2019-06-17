/**
 * gleich wie Obertyp
 */

public interface SwarmAnimal extends SocialAnimal {

    /**
     * Calling this method causes the animal to join the swarm,
     * depending on a specific probability.
     */
    void swarm();

    /**
     * After calling this method, the animal is no longer in a swarm.
     */
    void leave();
}
