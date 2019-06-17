import java.util.Iterator;

/**
 * Stores Objects in form of a Double-Linked-List, type information of the Objects will be lost.
 * All data is casted to Object.
 */
public class DLinkedList implements Iterable{
    /**
     * First Element of a DLinkedList
     */
    private ListNode FirstElement;

    /**
     * adds an element of type Object to the end of the DLinkedList
     * @param input Object to add
     */
    public void add(Object input){
        if(this.FirstElement == null){
            this.FirstElement = new ListNode(input);
        }
        else{
            this.FirstElement.add(input);
        }
    }

    /**
     * removes the first Object which is equal to @param input to the DLinkedList
     * @param input Object to remove
     */
    public void remove(Object input){
        this.FirstElement = this.FirstElement.remove(input);
    }

    /**
     * Clears all elements from the DLinkedList
     */
    public void clear(){
        this.FirstElement = null;
    }

    /**
     * Returns a iterator over the List, ordered from the first to the last element
     * @return iterator for the DLinkedList
     */
    @Override
    public Iterator iterator() {
        return new ListIterator(this.FirstElement);
    }

    /**
     * Iterator for the DLinkedList
     */
    private class ListIterator implements Iterator{
        private ListNode NextElem;

        /**
         * Sets the first element where the iterator starts
         * @param FirstNode first element where the iterator starts
         */
        public ListIterator(ListNode FirstNode) {
            this.NextElem = FirstNode;
        }

        /**
         * @return true, when the NextElem has a next element.
         */
        @Override
        public boolean hasNext() {
            if(this.NextElem == null) return false;
            else return true;
        }

        /**
         *
         * @return next element of the iterator
         */
        @Override
        public Object next() {
            Object tmp = this.NextElem.getValue();
            this.NextElem = this.NextElem.getNext();
            return tmp;
        }
    }

    /**
     * Represents a ListNode for the DLinkedList, elements are stored as Objects,
     * this causes that type information will be lost, when adding elements.
     */
    private class ListNode{
        /**
         * data of ListNode
         */
        private Object value;
        /**
         * Previous ListNode
         */
        private ListNode prev;
        /**
         * next ListNode
         */
        private ListNode next;


        public ListNode(Object input){
            this.value = input;
        }

        /**
         * Adds an Object to the first ListNode, which has no next ListNode and adds there @param
         * @param input Object to add
         */
        public void add(Object input){
            if(this.next == null){
                ListNode temp = new ListNode(input);
                temp.prev = this;
                this.next = temp;
            }
            else{
                this.next.add(input);
            }
        }

        /**
         *  finds the first Object found which is equal to @param input, and deletes it from the DLinkedList
         * @param input Object to find
         * @return Object at this position
         */
        public ListNode remove(Object input){
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
                ListNode lastNode = this;

                for (ListNode i = this.next ; i.next != null; i = i.next) {
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
         *
         * @return data whicht is stored in the ListNode
         */
        public Object getValue(){
            return this.value;
        }

        /**
         *
         * @return the succeeding ListNode
         */
        public ListNode getNext(){
            return this.next;
        }

    }

}