

abstract class abstractPack {
    /**
     * Name of this pack. Must be distinct
     */
    protected String name;

    /**
     * @return returns the Name of this Pack
     */
    public String getName() {
        return name;
    }

    /**
     * Prints the data of all Packs in this AllPacks
     */
    void print() {
        System.out.println(this.toString());
    }



}
