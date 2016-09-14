package hw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleEndedLLTester {
	   private DoubleEndedLL<Integer> list = new DoubleEndedLL<Integer>();
	   @Test
	   public void setUp(){
	      	      list.addFirst(new Integer(10));
	   }
	   @Test
	   public void testIsEmpty(){
		
	      list.addFirst(new Integer(10));
	      assertFalse(list.isEmpty());
	   }
	   @Test
	   public void testSize(){
		   	      list.addFirst(new Integer(10));
	      assertEquals(1, list.size());
	   }
	   @Test
	   public void testAddFirst(){
	      list.addFirst(new Integer(10));
	      assertEquals(1, list.size());
	   }
	   @Test
	   public void testAddLast(){
	      list.addLast(new Integer(10));
	      assertEquals(1, list.size());
	   }
	   @Test
	   public void testRemoveFirst(){
	      list.addFirst(new Integer(10));
	      list.removeFirst();
	      assertTrue(list.isEmpty());

	   }
	   @Test
	   public void testRemoveLast(){
	      list.addLast(new Integer(10));
	      list.removeLast();
	      assertTrue(list.isEmpty());
	   }
	

	   
}
