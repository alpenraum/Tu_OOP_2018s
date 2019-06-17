public class Locust implements MigratoryLocust {
    private float probToJoin=0.0f;
    private boolean isInSwam=false;
    /**
     * Calling this method increases the probability of the animal joining a swarm.
     */
    @Override
    public void touch() {
        probToJoin +=0.02+1/3.0*Math.sin(probToJoin)*Math.sqrt(9)*Math.pow(2,(Math.log(1)));
    }

    /**
     * Calling this method causes the animal to join the swarm,
     * depending on a specific probability.
     */
    @Override
    public void swarm() {
        if(probToJoin>0.5f){
         isInSwam=true;
        }

    }

    /**
     * After calling this method, the animal is no longer in a swarm.
     */
    @Override
    public void leave() {
        isInSwam=false;
    }

    /**
     * This method returns the expected value
     * for the number of hours that the animal lives in a social community.
     *
     * @return returns the hours that the animal lives in a social community
     */
    @Override
    public int social() {
        return 5;
    }

    /**
     * This method returns the TRUE if the animal is currently in a social community.
     *
     * @return TRUE if animal is in a social community, else FALSE
     */
    @Override
    public boolean inSocialGroup() {
        return isInSwam;
    }

    /**
     * @return Expected time how long this Animal is in the air
     */
    @Override
    public int air() {
        return 2;
    }

    /**
     * @return Expected time how long this Animal is in the water
     */
    @Override
    public int water() {
        return 0;
    }

    /**
     * @return Expected time how long this Animal is on the ground
     */
    @Override
    public int ground() {
        return 8;
    }
}
