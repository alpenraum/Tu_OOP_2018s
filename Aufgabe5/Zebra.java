import java.util.function.Predicate;

public class Zebra implements SteppeHerdAnimal {
    /**
     * The static value which gets compared with pattern of a Zebra.
     * If predCondition is smaller than pattern, the Predicate will return TRUE
     */
    private static float predCondition;
    /**
     * Value between 0.0 and 1.0 which determines the probability, that a Zebra will get stung by Mosquitos
     * 0.0 means it will always get stung
     * 1.0 means it will never get stung
     */
    private float pattern;
    /**
     * The maximum Speed a Zebra can achieve in a small Distance
     */
    private int velocity;
    /**
     * TRUE, if a Zebra is female
     */
    private boolean isFemale;

    public Zebra(int velocity, boolean isFemale, float pattern) {
        this.velocity = velocity;
        this.isFemale = isFemale;
        this.pattern = pattern;

    }

    /**
     * Returns the static Predicate for the Class Zebra
     *
     * @return Predicate<Zebra>
     */
    public static Predicate<Zebra> testZebra() {
        return i -> i.protection() > predCondition;
    }

    /**
     * sets the boundary which is tested by the Predicate
     * <p>
     * MUST BE called before any checks with the Zebra-Predicate are done
     *
     * @param f boundary for the fitness. Everything higher than n will return true if tested
     */
    public static void setPredCondition(float f) {
        predCondition = f;
    }

    /**
     * returns whether Zebra @param this is in a hierarchical SocialGroup
     *
     * @return TRUE
     */
    public boolean hierarchical() {
        return true;
    }

    /**
     * returns whether Zebra @param this is able to be an Alpha.
     * May only return TRUE, if hierarchical() returns TRUE
     * May only return TRUE, if Zebra is Female
     * May only return TRUE, if Zebras max. velocity is greater than a fixed value
     *
     * @return :
     * TRUE, if Zebra @param this is able to be Alpha
     * FALSE, if Zebra @param this is not able to be Alpha
     */
    public boolean mayBeAlpha() {
        return this.hierarchical() && this.isFemale && this.velocity > 20;
    }

    /**
     * @return the value of Protection between 0.0 and 1.0
     * 0.0 means no Protection
     * 1.0 means full Protection
     */
    public float protection() {
        return this.pattern;
    }

    /**
     * Compares Zebra @param this and FitAnimal @param that according to their velocity. Higher velocity means fitter.
     *
     * @param that FitAnimal, which @param this is compared to
     * @return :
     * 1, if Zebra @param this is much faster than FitAnimal @param that
     * 0, if both FitAnimals are almost equally fast
     * -1, if Zebra @param that is much faster than FitAnimal @param this
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
     * Changes the fitness of Zebra @param this
     *
     * @param value The number the fitness gets changed to; can be negative
     */
    public void changeFitness(int value) {
        this.velocity = value;
    }

    @Override
    public String toString() {
        return "Zebra{" +
                "pattern=" + pattern +
                ", velocity=" + velocity +
                ", isFemale=" + isFemale +
                '}';
    }
}

