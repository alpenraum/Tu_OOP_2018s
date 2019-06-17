public class Whale implements SchoolAnimal {
    private boolean isInSocialGroup=true;
    /**
     * returns the expected hours of this animal being in the air;
     * @return 0
     */
    @Override
    public int air() {
        return 0;
    }
    /**
     * returns the expected hours of this animal being in the water;
     * @return the expected hours of this animal being in the water;
     */
    @Override
    public int water() {
        return 420;
    }
    /**
     * returns the expected hours of this animal being on the ground;
     * @return 0
     */
    @Override
    public int ground() {
        return 0;
    }

    /**
     * This method returns the expected value
     * for the number of hours that the animal lives in a social community.
     * @return returns the hours that the animal lives in a social community
     */
    @Override
    public int social() {
        return 420;
    }

    /**
     * This method returns the TRUE if the animal is currently in a social community.
     * @return TRUE if animal is in a social community, else FALSE
     */
    @Override
    public boolean inSocialGroup() {
        return isInSocialGroup;
    }
}
