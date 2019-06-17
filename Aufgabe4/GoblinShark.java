public class GoblinShark implements Fish {

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
        return Integer.MAX_VALUE;
    }

    /**
     * @return returns 0
     */
    @Override
    public int ground() {
        return 0;
    }


}
