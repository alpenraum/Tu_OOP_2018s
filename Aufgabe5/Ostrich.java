import java.util.function.Predicate;

public class Ostrich implements SteppeHerdAnimal {
    /**
     * The static value which gets compared with power of an Ostrich.
     * If predCondition is smaller than power, the Predicate will return TRUE
     */
    private static int predCondition;
    /**
     * The maximum Speed a Zebra can achieve in a small Distance
     */
    private int velocity;
    /**
     * Value which determines the strength of an Ostrich in Newton
     */
    private int power;

    public Ostrich(int velocity, int power) {
        this.velocity = velocity;
        this.power = power;
    }

    /**
     * Returns the Predicate for the Class Ostrich
     *
     * @return Predicate<Ostrich>
     */
    public static Predicate<Ostrich> testOstrich() {
        return i -> i.power() > predCondition;
    }

    /**
     * sets the boundary which is tested by the Predicate
     * <p>
     * MUST BE called before any checks with the Ostrich-Predicate are done
     *
     * @param n boundary for the fitness. Everything higher than n will return true if tested
     */
    public static void setPredCondition(int n) {
        predCondition = n;
    }

    /**
     * returns whether Ostrich  this is in a hierarchical SocialGroup
     *
     * @return FALSE
     */
    public boolean hierarchical() {
        return false;
    }

    /**
     * returns whether Ostrich @param this is able to be an Alpha.
     * May only return TRUE, if hierarchical() returns TRUE
     *
     * @return FALSE
     */
    public boolean mayBeAlpha() {
        return hierarchical() && false;
    }

    /**
     * Compares Ostrich @param this and FitAnimal @param that according to their velocity. Higher velocity means fitter.
     *
     * @param that FitAnimal, which @param this is compared to
     * @return :
     * 1, if Ostrich @param this is much faster than FitAnimal @param that
     * 0, if both FitAnimals are almost equally fast
     * -1, if Ostrich @param that is much faster than FitAnimal @param this
     */
    public int fitter(FitAnimal that) {
        int result = 0;

        if (this.getFitness() - that.getFitness() >= 10) {
            result = 1;
        }
        if (this.getFitness() - that.getFitness() <= -10) {
            result = -1;
        }
        return result;
    }

    /**
     * @return value of fitness
     */
    public int getFitness() {
        return this.velocity;
    }

    /**
     * Changes the fitness of Ostrich @param this
     *
     * @param value The number the fitness gets changed to; can be negative
     */
    public void changeFitness(int value) {
        this.velocity = value;
    }

    /**
     * returns the power of Ostrich @param this in Newton
     *
     * @return power of Ostrich in Newton
     */
    public int power() {
        return this.power;
    }

    public String toString() {

        return "Ostrich{" +
                "velocity=" + velocity +
                ", power=" + power +
                '}';
    }
}
