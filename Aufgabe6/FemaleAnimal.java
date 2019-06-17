/**
 * This is a Female Animal
 */
public class FemaleAnimal extends Animal {

    private int numberOfChildren;

    /**
     * Creates a MaleAnimal
     * @param id Number by which this MaleAnimal is identified. Must be distinct
     * @param birthday minutes passed from 1.Jan.2000 to the birthday of the Animal
     */
    public FemaleAnimal(int id, long birthday){
        this.setId(id);
        this.setBirthday(birthday);
        this.setRank(false, 0);
        this.setAdrenalinSet(new DLinkedList());
        this.addAdrenalinValue(0,0);
        this.setCortisolSet(new DLinkedList());
        this.addCortisolValue(0,0);
        this.numberOfChildren=0;
    }


    /**
     * Sets the number of children this FemaleAnimal has given birth to
     * @param numberOfChildren New Number of children
     */
    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    /**
     *
     * @return returns the number of children this FemaleAnimal has given birth to
     */
    public int getNumberOfChildren() {
        return numberOfChildren;
    }


}
