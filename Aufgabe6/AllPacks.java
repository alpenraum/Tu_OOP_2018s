/**
 * A List of Packs.
 */
public class AllPacks extends abstractPack {
    /**
     * List of all Packs in this AllPacks
     */
    private DLinkedList list;


    /**
     * Creates a new AllPacks
     *
     * @param name Name of this AllPacks
     */
    public AllPacks(String name) {
        this.list = new DLinkedList();
        this.name = name;

    }

    /**
     * Returns the Pack with the same name as @param name. If there is no Pack in AllPacks with
     * that name, the method returns null.
     * @param name name of the Pack
     * @return Pack with the same name as @param name
     */
    public Pack getPack(String name){
        Pack result = null;

        for (Object i:
             list) {
            Pack j = (Pack) i;
            if(j.getName().equals(name)) result = j;
        }

        return result;
    }

    /**
     * Adds a Pack to this AllPacks
     *
     * @param that Pack which is going to be added
     */
    public void add(Pack that) {
        list.add(that);
    }

    /**
     * Removes the first Pack with the corresponding @param name.
     * If two Packs have the same Name equal to @param name, the first one found will be removed first.
     * If no Pack with the corresponding name is found, nothing happens.
     *
     * @param name Name of the pack which should be removed
     */
    public void remove(String name) {
        for (Object that : list) {
            Pack j = (Pack) that;

            if (j.getName().equals(name)) {
                this.list.remove(j);
            }
        }
    }

    public String toString() {
        String out = "";

        for (Object that : list) {
            Pack j = (Pack) that;
            out = out + "" + that.toString() + "\n";
        }
        return out;
    }
}
