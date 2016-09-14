package hw4;
import java.util.*;
public class MyQueue<T> implements Stack_QueueInterface<T>{
   private T[] Arr;
   private int front, rear, count;

   public MyQueue(){
      Arr = (T[])(new Object[10]);
      front= 0;
      rear = 0;
      count = 0;
   }
   public boolean isEmpty(){
      if(count	 == 0){
         return true;
      }
      else{

         return false;}
   }

   public int size(){
      return count;
   }
   public void addElement(T element){
      if(size() == Arr.length){
         this.resize();
      }
      Arr[rear] = element;
      rear = (rear + 1) % Arr.length;
      count++;

   }


   public T removeElement()throws NoSuchElementException{
      if(size() == 0){
         throw new NoSuchElementException();
      }

      int fakeF = front;
      front = (front + 1) % Arr.length;
      count--;
      return Arr[fakeF];



   }
   public void resize(){
      T[] Arr2 = (T[])(new Object[Arr.length * 2]);
      for(int i = 0; i < count; i++){
         Arr2[i] = Arr[front];
         front = (front + 1) % Arr.length;
      }
      front = 0;
      Arr = Arr2;
   }
}
