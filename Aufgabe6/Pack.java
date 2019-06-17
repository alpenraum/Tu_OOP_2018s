import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * A Pack of Animals. Can contain MaleAnimals and FemaleAnimals
 */
public class Pack extends abstractPack {
    /**
     * List of all MaleAnimals in this Pack
     */
    private DLinkedList maleList;
    /**
     * List of all FemaleAnimals in this Pack
     */
    private DLinkedList femaleList;


    /**
     * Creates a Pack
     *
     * @param name Name of this Pack. Must be distinct
     */
    public Pack(String name) {
        this.maleList = new DLinkedList();
        this.femaleList = new DLinkedList();
        this.name = name;
    }

    /**
     * @return returns the minutes passed since January 1st 2000 up to the current time (System time).
     */
    public static long currDateinMin() {
        LocalDate dateBefore = LocalDate.of(2000, 1, 1);
        LocalDate dateNow = LocalDate.now();

        return ChronoUnit.DAYS.between(dateBefore, dateNow) * 1440;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Adds an Animal to this pack.
     *
     * @param a Animal to add
     */
    public void add(Animal a) {
        if (a.getClass() == FemaleAnimal.class) {
            this.femaleList.add(a);
        } else {
            this.maleList.add(a);
        }

    }

    /**
     * Removes the first found Animal with the corresponding ID.
     * If a MaleAnimal and a FemaleAnimal have the same ID, the MaleAnimal will be removed first
     * If no Animal has the corresponding ID, nothing happens
     *
     * @param id Identification of the Animal that should be removed
     */
    public void remove(int id) {
        for (Object that : maleList) {
            MaleAnimal j = (MaleAnimal) that;
            if (j.getId() == id) {
                this.maleList.remove(j);
                return;
            }
        }
        for (Object that : femaleList) {
            FemaleAnimal j = (FemaleAnimal) that;
            if (j.getId() == id) {
                this.femaleList.remove(j);
                return;
            }
        }

    }

    public Animal getAnimal(int id){

        for (Object i:
             maleList) {
            MaleAnimal j = (MaleAnimal) i;
            if (j.getId() == id) return j;
        }

        for (Object i:
                femaleList) {
            FemaleAnimal j = (FemaleAnimal) i;
            if (j.getId() == id){
                return j;
            }
        }
        return null;
    }


    /**
     * @return returns the number of Animals in this Pack
     */
    public int packSize() {
        return maleSize() + femaleSize();
    }

    /**
     * @return returns the number of MaleAnimals in this Pack
     */
    private int maleSize() {
        int count = 0;
        for (Object that : maleList) {
            count++;
        }
        return count;
    }

    /**
     * @return returns the number of FemaleAnimals in this Pack
     */
    private int femaleSize() {
        int count = 0;
        for (Object that : femaleList) {
            count++;
        }
        return count;
    }

    /**
     * Calculates the mean Age of this Pack.
     * Pack must have at least one MaleAnimal or FemaleAnimal.
     * If that is not the case, the function will return -1 for every Average Age
     *
     * @return Array with three Values:
     * [0] = the average Age for all Animals in this Pack (-1 if no Animals in this Pack)
     * [1] = the average Age for all MaleAnimals in this Pack (-1 if no MaleAnimals in this Pack)
     * [2] = the average Age for all FemaleAnimals in this Pack (-1 if no FemaleAnimals in this Pack)
     */
    public float[] meanAge() {
        long currMin = currDateinMin();

        //in days

        float avgAgeMale = 0;
        float avgAgeFemale = 0;
        for (Object that : maleList) {
            MaleAnimal j = (MaleAnimal) that;
            long temp = (currMin - j.getBirthday()) / 1440;
            avgAgeMale += temp;
        }
        for (Object that : femaleList) {
            FemaleAnimal j = (FemaleAnimal) that;
            long temp = (currMin - j.getBirthday()) / 1440;
            avgAgeFemale += temp;
        }
        float avgAge = avgAgeFemale + avgAgeMale;


        int size = packSize();
        int sizeM = maleSize();
        int sizeF = femaleSize();

        if (size > 0) {
            avgAge /= size;
        } else {
            avgAge = -1;
        }
        if (sizeM > 0) {
            avgAgeMale /= sizeM;
        } else {
            avgAgeMale = -1;
        }
        if (sizeF > 0) {
            avgAgeFemale /= sizeF;
        } else {
            avgAgeFemale = -1;
        }
        return new float[]{avgAge, avgAgeMale, avgAgeFemale};
    }

    /**
     * Calculates the average children every FemaleAnimal in this Pack has
     * Pack must have at least one FemaleAnimal. If not, the function will return -1
     *
     * @return Integer of average children per FemaleAnimal (-1 if no FemaleAnimals in this Pack)
     */
    public float meanChildren() {
        int children = 0;
        for (Object that : femaleList) {
            FemaleAnimal j = (FemaleAnimal) that;
            children += j.getNumberOfChildren();
        }
        int sizeF = femaleSize();
        return sizeF > 0 ? (children / sizeF) : -1;
    }

    /**
     * Calculates the mean Cortisol level of this Pack
     * Pack must have at least one Alpha Animal(=Animal for that getAlphaDate()!=-1) and one Beta Animal(=Animal for that getAlphaDate()==-1).
     * If that is not the case, the function will return -1 for every Average Cortisol level
     *
     * @return Array with three Values:
     * [0] = the average Cortisol level for all Animals in this Pack (-1 if no Animals in this Pack)
     * [1] = the average Cortisol level for all Alpha Animals in this Pack (-1 if no Alpha Animals in this Pack)
     * [2] = the average Cortisol level for all Beta Animals in this Pack (-1 if no Beta Animals in this Pack)
     */
    public float[] meanCortisolLevel() {
        float meanCAlpha = 0;
        float meanCBeta = 0;
        float alphaCount = 0;
        float betaCount = 0;

        for (Object that : maleList) {
            MaleAnimal j = (MaleAnimal) that;
            if (j.getAlphaDate() == -1) {
                meanCBeta += j.getCortisolValue();
                betaCount++;
            } else {
                meanCAlpha += j.getCortisolValue();
                alphaCount++;
            }
        }
        for (Object that : femaleList) {
            FemaleAnimal j = (FemaleAnimal) that;
            if (j.getAlphaDate() == -1) {
                betaCount++;
                meanCBeta += j.getCortisolValue();
            } else {
                meanCAlpha += j.getCortisolValue();
                alphaCount++;
            }
        }

        float meanC = meanCAlpha + meanCBeta;

        int size = packSize();


        if (size > 0) {
            meanC /= size;
        } else {
            meanC = -1;
        }
        if (alphaCount > 0) {
            meanCAlpha /= alphaCount;
        } else {
            meanCAlpha = -1;
        }
        if (betaCount > 0) {
            meanCBeta /= betaCount;
        } else {
            meanCBeta = -1;
        }

        return new float[]{meanC, meanCAlpha, meanCBeta};
    }

    /**
     * Calculates the average Adrenalin level of all Beta Animals(=Animal for that getAlphaDate()==-1) of this Pack
     * Pack must have at least one Beta Animal. If not, this function will return -1
     *
     * @return Integer of average Adrenalin level per Beta (-1 if no Beta Animal in this Pack)
     */
    public float meanAdrenalin() {
        float betaCount = 0;
        float meanAdrenalin = 0;

        for (Object that : maleList) {
            MaleAnimal j = (MaleAnimal) that;
            if (j.getAlphaDate() == -1) {
                betaCount++;
                meanAdrenalin += j.getAdrenalinValue();
            }
        }

        for (Object that : femaleList) {
            FemaleAnimal j = (FemaleAnimal) that;
            if (j.getAlphaDate() == -1) {
                betaCount++;
                meanAdrenalin += j.getAdrenalinValue();
            }
        }

        if (betaCount > 0) {
            meanAdrenalin /= betaCount;
        } else {
            meanAdrenalin = -1;
        }
        return meanAdrenalin;
    }

    /**
     * Calculates the average Duration that an Animal is Alpha, of all Alpha Animals(=Animal for that getAlphaDate()!=-1) of this Pack
     * Pack must have at least one Alpha Animal. If not, this function will return -1
     *
     * @return Integer of Duration of being Alpha (In Minutes) per Alpha (-1 if no Alpha Animal in this Pack)
     */
    public float meanAlphaDur() {
        float currMin = currDateinMin();
        float avgDur = 0;
        float alphaCount = 0;

        for (Object that : maleList) {
            MaleAnimal j = (MaleAnimal) that;
            if (j.getAlphaDate() != -1) {
                alphaCount++;
                avgDur += (currMin - (j.getAlphaDate() + j.getBirthday())) / 1440;
            }
        }
        for (Object that : femaleList) {
            FemaleAnimal j = (FemaleAnimal) that;
            if (j.getAlphaDate() != -1) {
                alphaCount++;
                avgDur += (currMin - (j.getAlphaDate() + j.getBirthday())) / 1440;
            }
        }
        if (alphaCount > 0) {
            avgDur /= alphaCount;
        } else {
            avgDur = -1;
        }
        return avgDur;
    }

    public String toString() {
        float[] avgAge = meanAge();
        float[] cortLevel = meanCortisolLevel();
        return this.getName() + ": \n Packsize: " + packSize()
                + ", \n average Age of all: " + avgAge[0]
                + ", \n average Age of male: " + avgAge[1]
                + ", \n average Age of female: " + avgAge[2]
                + ", \n average number of children: " + meanChildren()
                + ", \n average Cortisollevel of all: " + cortLevel[0]
                + ", \n average Cortisollevel of Alpha: " + cortLevel[1]
                + ", \n average Cortisollevel of Beta: " + cortLevel[2]
                + ", \n average Adrenalinlevel of all beta: " + meanAdrenalin()
                + ", \n average duration of alpha status: " + meanAlphaDur();

    }
}
