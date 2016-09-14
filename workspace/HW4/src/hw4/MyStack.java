package hw4;
public class MyStack<T> implements Stack_QueueInterface<T>{
   DoubleEndedLL<T> list = new DoubleEndedLL<T>();
   public boolean isEmpty(){
      return list.isEmpty();
   }
   public void addElement(T newItem){
      list.addFirst(newItem);
   }
   public T removeElement(){
      return list.removeFirst();
   }
   public int size(){
      return list.size();
   }
}

