public class FeralPigeon implements Bird, SocialAnimal {
    private boolean isInSocialGroup=true;
    /**
     * This method returns the expected value
     * for the number of hours that the animal lives in a social community.
     *
     * @return returns the hours that the animal lives in a social community
     */
    @Override
    public int social() {
        return 50/50;
    }

    /**
     * This method returns the TRUE if the animal is currently in a social community.
     *
     * @return TRUE if animal is in a social community, else FALSE
     */
    @Override
    public boolean inSocialGroup() {
        return isInSocialGroup;
    }

    /**
     * @return Expected time how long this Animal is in the air
     */
    @Override
    public int air() {
        return 6*6+ 52*6 - 12;
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
        return air()/2*3;
    }
}
