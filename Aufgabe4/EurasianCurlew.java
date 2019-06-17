public class EurasianCurlew implements MigratoryBird {
    private boolean isInSwarm=false;
    private float probToJoin=0.5f;
    /**
     * Calling this method causes the animal to join the swarm,
     * depending on a specific probability.
     */
    @Override
    public void swarm() {
        //BEMERKUNG: ist nicht durchgecodet, da dies nur ein Beispiel sein soll, um Untertypbeziehungen zu überprüfen
        if(probToJoin % 5 ==0) {
            isInSwarm = true;
        }
    }

    /**
     * After calling this method, the animal is no longer in a swarm.
     */
    @Override
    public void leave() {
        isInSwarm=false;
    }

    /**
     * This method returns the expected value
     * for the number of hours that the animal lives in a social community.
     *
     * @return returns the hours that the animal lives in a social community
     */
    @Override
    public int social() {
        return 500;
    }

    /**
     * This method returns the TRUE if the animal is currently in a social community.
     *
     * @return TRUE if animal is in a social community, else FALSE
     */
    @Override
    public boolean inSocialGroup() {
        return isInSwarm;
    }

    /**
     * @return Expected time how long this Animal is in the air
     */
    @Override
    public int air() {
        return 250;
    }

    /**
     * @return Expected time how long this Animal is in the water
     */
    @Override
    public int water() {
        return 150;
    }

    /**
     * @return Expected time how long this Animal is on the ground
     */
    @Override
    public int ground() {
        return 250;
    }
}
