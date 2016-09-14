package hw7pack;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;
import java.util.*;

public class BSTreeTester {
	BSTree theTree = new BSTree();
	BSTree theSecond = new BSTree();
	BSTree theSorted = new BSTree();
	
	@Before
	public void start(){
		theSorted.insert ("one", 1);
		theSorted.insert ("two", 2);
		theSorted.insert ("three", 3);
		theSorted.insert ("four", 4);
		theSorted.insert ("five", 5);
		theSorted.insert ("six", 6);
		theTree.insert("ten", 10);
		theTree.insert("eight", 8);
		theTree.insert("eleven", 11);
		theTree.insert("twelve", 12);
		
		
	}
	@Test
	public void testInsert(){
		theTree.insert("one", 1);
		theSecond.insert("five", 5);
		assertTrue(theTree.findPerson(1, "one"));
		assertTrue(theSecond.findPerson(5, "five"));
		assertFalse(theTree.findPerson(10, "I am not ten"));
	}
	@Test
	public void testFind(){
		assertFalse(theSecond.findPerson(3, "I wonder if i am empty or not"));
		assertTrue(theTree.findPerson(10, "ten"));
		assertTrue(theTree.findPerson(12, "twelve"));
	    assertFalse(theTree.findPerson(12, "not tweleve"));
		assertTrue(theTree.findPerson(8, "eight"));
	}
	@Test
	public void testPrintTo(){
		Person[] sortedArray = theSorted.printToArray(theSorted.root);
		assertEquals(sortedArray.length, 6);
		assertEquals(sortedArray[0].getKey(), 1);
		assertEquals(sortedArray[1].getKey(), 2);
		assertEquals(sortedArray[2].getKey(), 3);
		assertEquals(sortedArray[3].getKey(), 4);
		assertEquals(sortedArray[4].getKey(), 5);
		assertEquals(sortedArray[5].getKey(), 6);
		Person[] array = theTree.printToArray(theTree.root);
		assertEquals(array.length, 4);
		assertEquals(array[0].getKey(), 8);
		assertEquals(array[1].getKey(), 10);
		assertEquals(array[2].getKey(), 11);
		assertEquals(array[3].getKey(), 12);
		
	}
	
	@Test
	public void testRoot(){
		assertEquals(theTree.getRoot().getPerson().getKey(), 10);
		assertEquals(theTree.getRoot().getPerson().getName(), "ten");
		assertEquals(theSecond.getRoot(), null);
		
	}
	@Test
	public void testDelete(){
		 theTree.delete(12, "twelve");
		 theTree.delete(10, "ten");
		assertFalse(theTree.findPerson(12, "twelve"));
		assertFalse(theTree.findPerson(10, "ten"));
		//assertEquals(new Person("eleven", 11), theTree.delete(11, "eleven"));
		
		
	}
	@Test
	public void testDepth(){
		assertEquals(theSecond.FindDepth(theSecond.root), 0);
		assertEquals(theTree.FindDepth(theTree.root), 0);
		assertEquals(theTree.FindDepth(theTree.root.getLChild()), 1);
		//assertEquals(theTree.FindDepth( new BSTNode(null, new Person("twelve", 12), null)), 2);
	}
	@Test 
	public void testLeaf(){
		assertEquals(theTree.leafCount(), 2);
		assertEquals(theSecond.leafCount(), 0);
		assertEquals(theSorted.leafCount(), 1);
		
	}
	@Test
	public void testLevel(){
		assertEquals(theSorted.levelCount(5), 1);
		assertEquals(theTree.levelCount(1), 2);
		assertEquals(theSecond.levelCount(0), -1);
		assertEquals(theTree.levelCount(2), 1);
	}
	

}


