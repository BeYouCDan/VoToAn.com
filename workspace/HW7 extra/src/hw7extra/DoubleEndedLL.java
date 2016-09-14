
package hw7extra;
import java.util.*;
public class DoubleEndedLL<T> extends AbstractList<T> implements DoubleEndedLLInterface<T>{
   private int nelems;
   private Node head;
   private Node tail;
   protected class Node{
      T data;
      Node next;
      public Node(T element){
         data = element;
         next = null;
      }
   }
   public DoubleEndedLL(){
	   head = null;
	   tail = null;
	   nelems = 0;
	   
   }
   public T get(int x){
      return head.data;
   }
   
   public boolean isEmpty(){
      if(head == null){
         return true; 
      }
      else{return false;}
   }
   public int size(){
      return nelems;
   }
   public void addFirst(T newItem){
      Node curNode = new Node(newItem);
      if(head == null){
         head = curNode;
         tail = curNode;
         nelems++;
         
      }
      else{
         curNode.next = head;
         head = curNode;
         nelems++;
         
      }
   }
   public void addLast(T newItem){
      Node curNode = new Node(newItem);
      if(head == null){
         head = curNode;
         tail = curNode;
         nelems++;
      }
      else{
         tail.next = curNode;
         tail = curNode;
      }
   }
   public T removeFirst(){
	  Node prevNode = head;
      Node curNode = head.next;
      head = null;
      head = curNode;
      nelems--;
      return prevNode.data;
   }
   public T removeLast(){
	   if(nelems == 1){
		   head = null;
		   tail = null;
		   return null;
		   
	   }
	   else{
      Node head2 = head;
      for(int i = 0; i < nelems -1; i++)
      {
         head2 = head2.next;
      } 
      head2.next = null;
      tail = head2;
      nelems--;
      return head2.data;
	   }
   }
}


