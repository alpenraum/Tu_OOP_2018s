public class Pinguin implements FlightlessBird, HerdAnimal {
    private Animal alpha=null;
    private Animal[] herd=new Animal[10];
    /**
     * @return returns 0
     */
    @Override
    public int air() {
        return 0;
    }

    /**
     * @return Expected time how long this Animal is in the water
     */
    @Override
    public int water() {
        return 414;
    }

    /**
     * @return Expected time how long this Animal is on the ground
     */
    @Override
    public int ground() {
        return 636;
    }

    /**
     * This method returns a reference of the alpha, when the animal has one.
     * Is the alpha not in the herd or the herd has not alpha it will returns "null".
     *
     * @return reference of the alpha, or "null" if it has no alpha in its herd
     */
    @Override
    public Animal getAlpha() {
        return alpha;
    }

    /**
     * Sets the new alpha of the animal.
     *
     * @param newAlpha reference of the new alpha
     */
    @Override
    public void setAlpha(Animal newAlpha) {
        alpha=newAlpha;
    }

    /**
     * After calling this method, the animal is no longer in a herd.
     */
    @Override
    public void leave() {
        herd=new Animal[herd.length];
    }

    /**
     * This method returns the expected value
     * for the number of hours that the animal lives in a social community.
     *
     * @return returns the hours that the animal lives in a social community
     */
    @Override
    public int social() {
        return air()+water()+ground();
    }

    /**
     * This method returns the TRUE if the animal is currently in a social community.
     *
     * @return TRUE if animal is in a social community, else FALSE
     */
    @Override
    public boolean inSocialGroup() {
        boolean isEmpty = true;
        for (Object ob : herd) {
            if (ob != null) {
                isEmpty = false;
                break;
            }
        }
        return !isEmpty;
    }
}
