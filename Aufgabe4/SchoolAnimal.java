/**
 * Wenn SchoolAnimal imp. wird, darf kein anderes Interface imp. werden.
 */

public interface SchoolAnimal extends Mammal, SocialAnimal {
    /**
     *
     * @return 0
     */
    @Override
    int air();

    /**
     *
     * @return 0
     */
    @Override
    int ground();
}
