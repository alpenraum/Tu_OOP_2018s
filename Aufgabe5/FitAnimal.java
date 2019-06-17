public interface FitAnimal<A> {

    /**
     * Compares the fitness of two FitAnimals.
     * FitAnimal <code>this</code> and FitAnimal @param that must be from the same SocialGroup
     * @param that
     * @return :
     *      1, if FitAnimal @param this is much fitter than FitAnimal @param that
     *      0, if both FitAnimals are almost equally fit
     *      -1, if FitAnimal @param that is much fitter than FitAnimal @param this
     */
    int fitter(FitAnimal that);

    /**
     * Changes the fitness of FitAnimal @param this
     * @param n The number the fitness gets changed to; can be negative
     */
    void changeFitness(int n);

    /**
     *  returns whether FitAnimal @param this is in a hierarchical SocialGroup
     * @return :
     *      TRUE, if the SocialGroup is hierarchical
     *      FALSE, if the SocialGroup is not hierarchical
     */
    boolean hierarchical();

    /**
     *  returns whether FitAnimal @param this is able to be an Alpha.
     *  May only return TRUE, if hierarchical() returns TRUE
     * @return :
     *      TRUE, if FitAnimal @param this is able to be Alpha
     *      FALSE, if FitAnimal @param this is not able to be Alpha
     */
    boolean mayBeAlpha();

    /**
     * returns the fitness of the FitAnimal
     * @return value of fitness
     */
    int getFitness();
}
