package hw4;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

import org.junit.Test;

public class MyQueueTester {
	MyQueue<Integer> que = new MyQueue<Integer>();
	@Test
	public void testMyQueue(){
		assertEquals(0, que.size());
		
	}
	@Test
	public void testIsEmpty(){
		que.addElement(new Integer(10));
		que.removeElement();
		assertTrue(que.isEmpty());
		
		
	}
	@Test
	public void testSize(){
		que.addElement(new Integer(10));
		assertEquals(1, que.size());
		que.removeElement();
		assertEquals(0, que.size());
		
	}
	@Test
	public void testAddElement(){
		que.addElement(new Integer(2));
		assertEquals(new Integer(2), que.removeElement());		
	}
	@Test
	public void testRemoveElement(){
		que.addElement(new Integer(10));
		assertEquals(new Integer(10), que.removeElement());
		
		
	}
	@Test
	public void testResize(){
		que.addElement(new Integer(10));
		int originalSize = que.size();
		que.resize();
		int afterSize = que.size();
		assertEquals(originalSize, afterSize);
	}

	@Test
	public void testExeption() {
		try{
			que.removeElement();
			fail();
			
		}
		catch(NoSuchElementException e){
			
		}
	}
	@Test
	public void testIllegaException(){
		//beacsue of resize() method the circular array 
		 //will always avoid IllegalStateException
		
		
	}

}
