/**
 * Wenn SocialAnimal imp. wird, muss ein Tier einer Spezies(Animal, Bird, Insect, Mammal) auch implementiert werden,
 * Es darf nur max. ein Untertyp von SocialAnimal imp. werden.
 *
 */

public interface SocialAnimal extends Animal{
    /**
     * This method returns the expected value
     * for the number of hours that the animal lives in a social community.
     * @return returns the hours that the animal lives in a social community
     */
    int social();

    /**
     * This method returns the TRUE if the animal is currently in a social community.
     * @return TRUE if animal is in a social community, else FALSE
     */
    boolean inSocialGroup();
}
