import java.util.function.Predicate;

public class Starling implements FitAnimal {
    /**
     * The static value which gets compared with power of an Ostrich.
     * If predCondition is smaller than fitness(), the Predicate will return TRUE
     */
    private static int predCondition;
    /**
     * The Stamina of a Starling
     */
    private int stamina;
    /**
     * Whether a Starling has the ability to turn fast
     * TRUE, if Starling can turn fast
     */
    private boolean canTurnFast;

    public Starling(int fitness, boolean canTurnFast) {
        this.stamina = fitness;
        this.canTurnFast = canTurnFast;
    }

    /**
     * Returns the Predicate for the Class Starling
     *
     * @return Predicate<Starling>
     */
    public static Predicate<Starling> testStarling() {
        return i -> i.getFitness() > predCondition;
    }

    /**
     * sets the boundary which is tested by the Predicate
     * <p>
     * MUST BE called before any checks of the Starling-Predicate are done
     *
     * @param n boundary for the fitness. Everything higher than n will return true if tested
     */
    public static void setPredCondition(int n) {
        predCondition = n;
    }

    /**
     * compares two Starling regarding their fitness and ability to turn fast.
     * Fitness is more important than the ability to turn fast
     *
     * @param that FitAnimal which gets compared to this
     * @return :
     * 1, if "this" is much fitter than "that"
     * 0, if both are equally fast and equal
     * -1, if "that" is much fitter than "this"
     */
    public int fitter(FitAnimal that) {
        int result = 0;
        //speed is much more important than fast turning <= not able to implement the check
        //the ability to turn fast, without casting

        if (this.getFitness() - that.getFitness() >= 10) {
            result = 1;
        } else if (this.getFitness() - that.getFitness() <= -10) {
            result = -1;
        }
        return result;

    }

    /**
     *
     * @param n The number the fitness gets changed to; can be negative
     */
    public void changeFitness(int n) {
        this.stamina = n;
    }

    /**
     * returns whether Starling @param this is in a hierarchical SocialGroup
     *
     * @return FALSE
     */
    public boolean hierarchical() {
        return false;
    }

    /**
     *  returns whether Starling @param this is able to be an Alpha.
     *  May only return TRUE, if hierarchical() returns TRUE
     * @return FALSE
     */
    public boolean mayBeAlpha() {
        return false;
    }

    /**
     *
     * @return value of fitness
     */
    public int getFitness() {
        return this.stamina;
    }

    public String toString() {
        return "Starling{" +
                "stamina=" + stamina +
                ", canTurnFast=" + canTurnFast +
                '}';
    }
}
