/**
 * Dae Kyung Kim
 * A12741934
 * cs12whz
 * 1/19/16
 */

package doubly;

import java.util.*;

public class DoublyLinkedList12<E> extends AbstractList<E> {

    private int nelems;
    private Node head;
    private Node tail;


    protected class Node {
        E data;
        Node next;
        Node prev;

        /** Constructor to create singleton Node */
        public Node(E element)
        {
            data = element;
        }
        /** Constructor to create singleton link it between previous and next 
         *   @param element Element to add, can be null
         *   @param prevNode predecessor Node, can be null
         *   @param nextNode successor Node, can be null 
         */
        public Node(E element, Node prevNode, Node nextNode)
        {
            data = element;
            next = nextNode;
            prev = prevNode;



        }
        /** Remove this node from the list. Update previous and next nodes */
        public void remove()//ask if this is the right implementation
        {
            this.prev.next = this.next;	
            this.prev = this.next.prev;

        }
        /** Set the previous node in the list
         *  @param p new previous node
         */
        public void setPrev(Node p)
        {
            prev = p;
        }
        /** Set the next node in the list
         *  @param n new next node
         */
        public void setNext(Node n)
        {
            next = n;
        }

        /** Set the element 
         *  @param e new element 
         */
        public void setElement(E e)
        {
            data = e;
        }
        /** Accessor to get the next Node in the list */
        public Node getNext()
        {
            return next; // XXX-CHANGE-XXX
        }
        /** Accessor to get the prev Node in the list */
        public Node getPrev()
        {
            return prev; // XXX-CHANGE-XXX
        } 
        /** Accessor to get the Nodes Element */
        public E getElement()
        {
            return data; // XXX-CHANGE-XXX
        } 
    }

    /** ListIterator implementation */ 
    protected class MyListIterator implements ListIterator<E> {

        private boolean forward;
        private boolean canRemove;
        private Node left,right, nowNode; // Cursor sits between these two nodes
        private int idx;        // Tracks current position. what next() would
        // return 
        public MyListIterator()
        {
            idx = 0;

            left = head;

            right = head.next;
        }
        @Override
            public void add(E e) throws  NullPointerException
            {
                nowNode = new Node(e);
                if(e == null){
                    throw new NullPointerException();
                }   

                else{
                    canRemove = false;
                    left.next = nowNode;
                    right.prev = nowNode;
                    nowNode.next = right;
                    nowNode.prev = left;
                    right = nowNode;

                }

            }
        @Override
            public boolean hasNext()
            {
                if(right == tail){
                    return false;

                }
                else{return true;}// XXX-CHANGE-XXX
            }

        @Override
            public boolean hasPrevious()
            {
                if(left == head){
                    return false;

                } 
                else{return true;}// XXX-CHANGE-XXX
            }
        @Override
            public E next() throws NoSuchElementException
            {
                if(right == null){
                    throw new NoSuchElementException();
                }
                else{
                    left = left.next;
                    right = right.next;
                    idx++;
                    forward = true;
                    canRemove = true;
                    return left.data;
                }

            }
        @Override
            public int nextIndex()
            {

                return idx + 1; // XXX-CHANGE-XXX
            }
        @Override
            public E previous() throws NoSuchElementException
            {
                if(left == null){
                    throw new NoSuchElementException();
                }
                else{
                    idx--;
                    left = left.prev;
                    right = right.prev;
                    forward = false;
                    canRemove = true;
                    return right.data;
                }
            }

        @Override
            public int previousIndex()
            {
                return idx - 1;  // XXX-CHANGE-XXX
            }
        @Override
            public void remove() throws IllegalStateException
            {
                if(canRemove == true){
                    if(forward == true){
                        left.prev.next = right;
                        right.prev = left.prev;
                        left = left.prev;
                    }
                    else{
                        left.next = right.next;
                        right.next.prev = left;
                        right = right.next;
                    }

                }
                else{
                    throw new IllegalStateException();
                }
            }
        @Override
            public void set(E e) throws NullPointerException,IllegalStateException
            {
                if(canRemove == true){
                    if(forward == true){
                        left.data = e;
                    }
                    else{
                        right.data = e;

                    }
                }
                else{
                    throw new IllegalStateException();
                }
            }

    }


    //  Implementation of the DoublyLinkedList12 Class


    /** Only 0-argument constructor is define */
    public DoublyLinkedList12()
    {
        tail = new Node(null);
        head = new Node(null);
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }

    @Override
        public int size()
        {
            // need to implement the size method
            return nelems;
        }

    @Override
        public E get(int index) throws IndexOutOfBoundsException
        {
            if(index < 0){
                throw new IndexOutOfBoundsException();
            }
            if(head.next == tail){
                throw new IndexOutOfBoundsException();

            }
            else{
                Node head2 = head;
                for(int counter = 0; counter < index; counter++){
                    head2 = head2.next;
                }

                return head2.data;
            }
        }

    @Override
        /** Add an element to the list 
         * @param index  where in the list to add
         * @param data data to add
         * @throws IndexOutOfBoundsException
         * @throws NullPointerException
         */ 
        public void add(int index, E data) throws IndexOutOfBoundsException,NullPointerException
        {
            Node curNode = new Node(data);

            if(index < 0){
                throw new IndexOutOfBoundsException();
            }

            else{
                Node head2 = head;
                for(int counter = 0; counter < index; counter++){

                    if(head2 == null){
                        throw new NullPointerException();
                    }
                    head2 = head2.next;
                }

                curNode.next = head2.next;
                curNode.prev = head2;
                head2.next.prev = curNode;
                head2.next = curNode;

                nelems++;



            }

        }
    /** Add an element to the end of the list 
     * @param data data to add
     * @throws NullPointerException
     */ 
    public boolean add(E data) throws NullPointerException//wh  en can you throw this exception?
    {
        if(head.prev != null){
            throw new NullPointerException();

        }
        Node curNode = new Node(data);
        if(head.next == tail){//question to ask or null?
            head.next = curNode;
            curNode.prev = head;
            curNode.next = tail;
            tail.prev = curNode;
            nelems++;
            return true;

        }
        else{
            tail.prev.next = curNode;
            curNode.prev = tail.prev;
            curNode.next = tail;
            tail.prev = curNode;
            nelems++;
            return true;

        } 
    }

    /** Set the element at an index in the list 
     * @param index  where in the list to add
     * @param data data to add
     * @return element that was previously at this index.
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */ 
    public E set(int index, E data) throws IndexOutOfBoundsException,NullPointerException
    {
        if(index < 0){
            throw new IndexOutOfBoundsException();

        }
        else{
            Node head2 = head;
            for(int counter = 0; counter < index; counter++){
                head2 = head2.next;
            }
            if(head2 == tail || head2 == head){
                throw new NullPointerException();
            }
            head2.data = data;
            return head2.data;

        }  
    }




    /** Remove the element at an index in the list 
     * @param index  where in the list to add
     * @return element the data found
     * @throws IndexOutOfBoundsException
     */ 
    public E remove(int index) throws IndexOutOfBoundsException
    {
        if(index < 0){
            throw new IndexOutOfBoundsException();

        }
        else{
            Node head2 = head;
            for(int counter = 0; counter < index; counter++){
                head2 = head2.next;
            }
            head2.prev.next = head2.next;
            head2.next.prev = head2.prev;
            return head2.data;
        }





    }

    /** Clear the linked list */
    public void clear()
    {
        Node head2 = head;
        for(int counter = 1; counter < nelems; counter++){
            head2 = head2.next;
            head2.data = null;
        }


    }

    /** Determine if the list empty 
     *  @return true if empty, false otherwise */
    public boolean isEmpty()
    {
        if(head.next == tail){
            return true;
        }
        else{return false;}  
    }


    // Helper method to get the Node at the Nth index
    private Node getNth(int index) 
    {
        Node head2 = head;
        for(int counter = 0; counter < index; counter ++){
            head2 = head2.next;
        }
        return head2;

    }




    /*  UNCOMMENT the following when you believe your MyListIterator class is
        functioning correctly*/
    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }
    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }


}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4


