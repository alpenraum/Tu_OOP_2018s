/**
 * Represents a Animal
 */
public abstract class Animal {

    /**
     * Number by which this Animal is identified. Must be distinct
     */
    private int id;
    /**
     * Birthday of this Animal.
     * Represented by the minutes passed from 1.Jan.2000 to the birthday of the Animal
     */
    private long birthday;
    /**
     * Current Rank of this Animal
     */
    private Rank rank;


    private DLinkedList AdrenalinSet;
    private DLinkedList CortisolSet;


    /**
     * @return Returns the unique Id of the Animal
     */
    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    protected void setRank(Rank rank) {
        this.rank = rank;
    }

    protected void setAdrenalinSet(DLinkedList adrenalinSet) {
        AdrenalinSet = adrenalinSet;
    }

    protected void setCortisolSet(DLinkedList cortisolSet) {
        CortisolSet = cortisolSet;
    }

    /**
     * @return minutes passed from 1.Jan.2000 to the birthday of the Animal
     */
    long getBirthday() {
        return birthday;
    }

    /**
     * Changes the rank of the Animal, and stores the date when the animal become alpha.
     * Date are the minutes passed from the birthday of the animal to the date it became alpha.
     * When changing the rank of the animal, only information
     * which are in both (alpha and beta) will be kept, other will information will be lost.
     * When a alpha animal is set to alpha, the Object will stay the same. Same with beta.
     *
     * @param toAlpha is true, the rank changes to alpha. Otherwise the Animal will become a beta.
     */
    public void setRank(boolean toAlpha, int date) {
        //Alpha to beta
        if (!toAlpha) {
            this.rank = new betaRank();
        }
        //Beta to alpha
        if (toAlpha) {
            this.rank = new alphaRank(date);
            this.AdrenalinSet.clear();
        }
    }

    /**
     * Returns the minutes passed between the Birthday of the Animal and the date it became Alpha
     * If this Animal is not Alpha, it will return -1
     *
     * @return :
     * If Animal is Alpha: Minutes between the birthday of the Animal and the date it became Alpha
     * If Animal is not Alpha: -1
     */
    int getAlphaDate() {
        return this.rank.getAlphaDate();
    }

    /**
     * Sets a new adrenalin value for the animal and adds it to the adrenalin set.
     * If the animal is an alpha the value is not added to the set.
     *
     * @param newValue adds the HormonValue to the adrenalin set if its a beta animal
     */
    void addAdrenalinValue(int time, float newValue) {
        if (!this.rank.isAlpha()) this.AdrenalinSet.add(new HormonValue(time,newValue));
    }

    /**
     * Returns the adrenalin level of the animal, at the specific date.
     * Date are the minutes passed from the birthday of the animal to the date when the adrenalin
     * level was set. The method returns -1 if the animal is an alpha or there is no Hormonlevel
     * with at the specific date.
     *
     * @param date date when the adrenalin value was set.
     * @return adrenalin level at the specific date
     */
    float getAdrenalinValue(int date) {
        float result = -1;

        for (Object i :
                this.CortisolSet) {
            HormonValue j = (HormonValue) i;
            if (j.getTime() == date) result = j.getLevel();
        }

        return result;

    }

    /**
     * Returns the latest adrenalin level of the animal.
     * The method returns -1 if there is no AdrenalinValue for this Animal.
     *
     * @return latest measured adrenalin level
     */
    float getAdrenalinValue() {
        float result = -1;

        for (Object that : AdrenalinSet) {
            HormonValue i = (HormonValue) that;
            result = i.getLevel();
        }
        return result;
    }

    /**
     * Sets a new cortisol value for the animal and adds it to the cortisol set.
     *
     * @param newValue adds the HormonValue to the cortisol set
     */
    void addCortisolValue(int time, float newValue) {
        this.CortisolSet.add(new HormonValue(time,newValue));
    }

    /**
     * Returns the cortisol level of the animal, at the specific date.
     * Date are the minutes passed from the birthday of the animal to the date when the cortisol
     * level was set. The method returns -1 if  there is no cortisol level with at the specific date.
     *
     * @param date date when the cortisol value was set.
     * @return value of the crotisol leval at the specific date
     */
    float getCortisolValue(int date) {
        float result = -1;

        for (Object i :
                this.CortisolSet) {
            HormonValue j = (HormonValue) i;
            if (j.getTime() == date) result = j.getLevel();
        }

        return result;
    }

    /**
     * Returns the latest cortisol level of the animal.
     * The method returns -1 if there is no CortisolValue for this Animal.
     *
     * @return latest measured cortisol level
     */
    float getCortisolValue() {
        float result = -1;

        for (Object that : CortisolSet) {
            HormonValue i = (HormonValue) that;
            result = i.getLevel();
        }
        return result;
    }

    ///////////////inner class
    /**
     * Stores information about a HormonValue
     */
     private class HormonValue {
        private int time;
        private float level;

        /**
         * Generates a new HormonValue.
         * @param time Date are the minutes passed from the birthday of the animal to the date when the
         *             value was measured.
         * @param level value of the hormon level
         */
         public HormonValue(int time, float level){
            this.time = time;
            this.level = level;
        }

        /**
         *
         * @return Date when the HormonValue was measured
         */
         public int getTime() {
            return time;
        }

        /**
         *
         * @return value of the hormon level
         */
         public float getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return "HormonValue{" +
                    "time=" + time +
                    ", level=" + level +
                    '}';
        }
    }


}
