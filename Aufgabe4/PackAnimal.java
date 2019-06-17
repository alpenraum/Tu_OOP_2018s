/**
 * Wenn PackAnimal imp. wird, darf kein anderes Interface imp. werden.
 */

public interface PackAnimal extends Mammal, HerdAnimal{
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
    int water();
}
