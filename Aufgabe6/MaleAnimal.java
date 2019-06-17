/**
 * This is a Male Animal
 */
public class MaleAnimal extends Animal {

    public MaleAnimal(int id, long birthday) {
        this.setId(id);
        this.setBirthday(birthday);
        this.setRank(false, 0);
        this.setAdrenalinSet(new DLinkedList());
        this.addAdrenalinValue(0,0);
        this.setCortisolSet(new DLinkedList());
        this.addCortisolValue(0,0);

    }

}
