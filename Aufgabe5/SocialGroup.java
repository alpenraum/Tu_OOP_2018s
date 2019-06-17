import java.util.Iterator;
import java.util.function.Predicate;

public class SocialGroup<A extends FitAnimal<? super A>> implements Iterable<A> {
    private DLinkedList<A> SocialCommunity;

    public SocialGroup() {
        this.SocialCommunity = new DLinkedList<>();
    }

    /**
     * adds an <A> to the SocialGroup
     * @param input FitAnimal of Type <A>
     */
    public void add(A input) {
        this.SocialCommunity.add(input);
    }

    /**
     * removes A from the SocialGroup
     * @param input Object which should be removes
     */
    public void remove(A input) {
        this.SocialCommunity.remove(input);
    }

    /**
     * Checks if every A in SocialGroup is able to live in a hierarchical SocialGroup
     * @return :
     *      TRUE, if every A in SocialGroup is able to live in a hierarchical SocialGroup
     *      FALSE, if at least one A of this SocialGroup can not live in a hierarchical SocialGroup
     */
    public boolean hierarchical() {
        return this.SocialCommunity.hierarchical();
    }


    /**
     * Returns an iterator over all Objects of this SocialGroup in descending order of their fitness
     *
     * @return an Iterator.
     */
    public Iterator<A> iterator() {
        return new FitnessIter<>(this.SocialCommunity, false);
    }

    /**
     * returns an iterator over all Objects of this SocialGroup in descending order of their fitness and able to be Alpha
     * @return an Iterator
     */
    public Iterator<A> alpha() {
        return new FitnessIter<>(this.SocialCommunity, true);
    }

    /**
     * moves an Object of SocialGroup<T> input into this SocialGroup, if Predicate<T> cond returns TRUE; iterates over all Objects of input
     * @param input SocialGroup<T> of Object which get moved potentially
     * @param cond Predicate<T> Condition, which gets checked for every Object of input. If TRUE, Object gets moved
     */

    public <T extends A> void move(SocialGroup<T> input, Predicate<T> cond) {

        for (T a : input) {
            if (cond.test(a)) {

                this.add(a);
                input.remove(a);
            }
        }
    }

    /**
     * Compares Objects of this SocialGroup regarding their fitness. If two Objects are found which are equally fit, one of them randomly wins
     * and gets a fitness boost of 5, the loser gets their fitness reduced by 5. rounds have to be smaller or equal to the number of A in SocialGroup.
     * One Round is over if the Object which is chosen first, loses. Then a new first Object gets elected
     * @param rounds amount of rounds, which this competition lasts
     */
    public void compareAll(int rounds) {
        Iterator<A> myIter = this.iterator();
        for (int i = 0; i < rounds; i++) {
            A animal1 = myIter.next();
            boolean isDefeated = false;
            while (!isDefeated) {
                for (A animal2 : this) {
                    //check dafür, ob ein Animal gegen sich selbst antritt, ist überflüssig, da sich an der Fitness
                    //nichts verändert (x+5-5=x)
                    if (animal1.fitter(animal2) == 0) {
                        int rand = (int) (Math.random() * 10);

                        if (rand < 5) { //animal 1 won
                            animal1.changeFitness(animal1.getFitness() + 5);
                            animal2.changeFitness(animal2.getFitness() - 5);
                        } else { //animal 2 won
                            animal1.changeFitness(animal1.getFitness() - 5);
                            animal2.changeFitness(animal2.getFitness() + 5);
                            isDefeated = true;
                        }
                    }
                }
            }
        }
    }
///////////inner class
    private class DLinkedList<A extends FitAnimal>{
    /**
     * First Element of a DLinkedList
     */
    private ListNode<A> FirstElement;

    /**
     * adds an Object of type A to the end of the DLinkedList
     * @param input Object to add
     */
        public void add(A input){
            if(this.FirstElement == null){
                this.FirstElement = new ListNode<>(input);
            }
            else{
                this.FirstElement.add(input);
            }
        }

    /**
     * checks whether every Object in this DLinkedList is in a hierarchical DLinkedList
     * @return :
     *      TRUE, if every Object in this DLinkedList is in a hierarchical DLinkedList
     *      FALSE, if at least one Object in this DLinkedList is not in a hierarchical DLinkedList
     */
    public boolean hierarchical(){
            return this.FirstElement.hierarchical();
        }

    /**
     * removes the first Object which is equal to @param input to the DLinkedList
     * @param input Object to remove
     */
    public void remove(A input){
            this.FirstElement = this.FirstElement.remove(input);
        }

    /**
     *
     * @return returns the first element of this DLinkedList
     */
        public ListNode<A> getFirstElement() {
            return this.FirstElement;
        }



    }
///////////inner class
    private class ListNode<A extends FitAnimal>{
    /**
     * data of ListNode
     */
    private A value;
    /**
     * Previous ListNode
     */
        private ListNode<A> prev;
    /**
     * next ListNode
     */
    private ListNode<A> next;


        public ListNode(A input){
            this.value = input;
        }

        /**
         * Adds an Object to the first ListNode, which has no @param next
         * @param input Object to add
         */
        public void add(A input){
            if(this.next == null){
                ListNode<A> temp = new ListNode<>(input);
                temp.prev = this;
                this.next = temp;
            }
            else{
                this.next.add(input);
            }
        }

        /**
         *  finds the first Object found which is equal to @param input
         * @param input Object to find
         * @return ListNode<A>
         */
        public ListNode<A> remove(A input){
            if(this.value == input){
                if(this.next == null){
                    return null;
                }
                else{
                    this.next.prev = null;
                    return this.next;
                }

            }
            else{
                ListNode<A> lastNode = this;

                for (ListNode<A> i = this.next ; i.next != null; i = i.next) {
                    if(i.value == input){
                        i.prev.next = i.next;
                        i.next.prev = i.prev;
                    }
                    lastNode = i.next;
                }

                if(lastNode.value == input){
                    lastNode.prev.next = null;

                }
                return this;
            }
        }

    /**
     * checks whether this ListNode is in a hierarchical Collection
     * @return :
     *      TRUE, if this ListNode and every succeeding ListNode is in a hierarchical Collection
     *      FALSE, if this Listnode or any of the succeeding ListNodes in not in a hierarchical Collection
     */
        public boolean hierarchical(){
            if(!this.value.hierarchical()){
                return false;
            }
            else if(this.next == null){
                return true;
            }
            else{
                return this.next.hierarchical();
            }
        }

        /**
         *
         * @return data of this ListNode
         */
        public A getValue(){
            return this.value;
        }

        /**
         *
         * @return the succeeding ListNode
         */
        public ListNode<A> getNext(){
            return this.next;
        }

    }
/////////////inner class
    private class FitnessIter<A extends FitAnimal>  implements Iterator<A> {
    /**
     * List which the Iterator iterates over
     */
    private DLinkedList<A> SocialCommunity;
    /**
     * stack where next() Objects are stored
     */
        private DLinkedList<A> temp;
    /**
     * The last Element which got returned
     */
    private A LastReturnedElement;
    /**
     * upper Limit of Fitness in this Iterator.
     * Corresponds to the highest fitness out of SocialCommunity
     */
        private int upperBorder;
    /**
     * TRUE, if the iterator reached the spot behind the last Object of SocialCommunity
     */
    private boolean allAnimalsReturned;
    /**
     * TRUE, if this FitnessIter is supposed to only iterate over Objects which are able to be alpha
     */
        private boolean onlyAlpha;



        public FitnessIter(DLinkedList<A> SocialCommunity, boolean onlyAlpha){
            this.upperBorder = Integer.MAX_VALUE;
            this.SocialCommunity = SocialCommunity;
            this.temp = new DLinkedList<>();
            boolean allAnimalsReturned = false;
            this.onlyAlpha = onlyAlpha;

            if(this.SocialCommunity.getFirstElement() != null) this.fillNext();
        }


        /**
         *
         * @return :
         *      TRUE, if there is atleast one Object in SocialCommunity left to iterate over
         *      FALSE, if this FitnessIter has reached the End
         */
        public boolean hasNext(){
            if(this.temp.getFirstElement() != null && !allAnimalsReturned) return true;
            else return false;
        }

        /**
         *
         * @return the next Object in SocialCommunity
         */
        public A next() {
            A result = this.temp.getFirstElement().getValue();
            this.temp.remove(result);
            if(this.temp.getFirstElement() == null){
                this.fillNext();
            }
            this.LastReturnedElement = result;
            return result;
        }

    /**
     * removes the element out of SocialCommunity, which got returned last
     */
    public void remove(){
            this.SocialCommunity.remove(this.LastReturnedElement);
        }

    /**
     * finds the next Objects in SocialCommunity in descending order to their fitness and puts in on the stack DLinkedList<A> temp.
     */
    private void fillNext(){
            int highestFitness = Integer.MIN_VALUE;
            boolean elementAdded = false;
            ListNode<A> LastElement = this.SocialCommunity.getFirstElement();

            //Get highest fitness < upperBorder and > highestFitness
            for (ListNode<A> i = this.SocialCommunity.getFirstElement(); i.getNext() != null; i = i.getNext()) {
                if(this.upperBorder > i.getValue().getFitness() &&
                        i.getValue().getFitness() > highestFitness && (i.getValue().mayBeAlpha() || !this.onlyAlpha)){
                    highestFitness =  i.getValue().getFitness();
                }
                LastElement = i.getNext();
            }
            if(this.upperBorder > LastElement.getValue().getFitness() &&
                    LastElement.getValue().getFitness() > highestFitness && (LastElement.getValue().mayBeAlpha() || !this.onlyAlpha)){
                highestFitness =  LastElement.getValue().getFitness();
            }

            this.upperBorder = highestFitness;

            for (ListNode<A> i = this.SocialCommunity.getFirstElement(); i.getNext() != null; i = i.getNext()) {
                if(highestFitness == i.getValue().getFitness()){
                    temp.add(i.getValue());
                    elementAdded = true;
                }
            }
            if(highestFitness == LastElement.getValue().getFitness()){
                temp.add(LastElement.getValue());
                elementAdded = true;
            }
            this.allAnimalsReturned = !elementAdded;
        }
    }

}
