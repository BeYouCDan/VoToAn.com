package doubly;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 *  Title: class LinkedListTester
 *  Description: JUnit test class for LinkedList class
 *  Dae Kyung Kim
 *  Cs12whz
 *  A12741934
 *  1/19/16
 * */

/*
 * You should modify the information above to add your name 
 * 
 * Finally, when your tester is complete, you will rename it DoublyLinkedList12Tester.java 
 * (renaming LinkedList to DoublyLinkedList12 everywhere in the file) so that you can
 * use it to test your DoublyLinkedList12 and MyListIterator classes.
 */
public class DoublyLinkedList12Tester
{
   private DoublyLinkedList12<Integer> empty ;
   private DoublyLinkedList12<Integer> one ;
   private DoublyLinkedList12<Integer> several ;
   private DoublyLinkedList12<String>  slist ;
   private DoublyLinkedList12<Integer>.MyListIterator iter;
   static final int DIM = 5;

   /**
    * Standard Test Fixture. An empty list, a list with one entry (0) and 
    * a list with several entries (0,1,2)
    */ 
   @Before
      public void setUp()
      {
         empty = new DoublyLinkedList12<Integer>();
         one = new DoublyLinkedList12<Integer>();
         one.add(0,new Integer(0));
         several = new DoublyLinkedList12<Integer>() ;
         // List: 1,2,3,...,Dim
         for (int i = DIM; i > 0; i--)
            several.add(0,new Integer(i));

         // List: "First","Last"
         slist = new DoublyLinkedList12<String>();
         slist.add(0,"First");
         slist.add(1,"Last");
      }
   /** Test if heads of the lists are correct */
   @Test
      public void testGetHead()
      {
         assertEquals("Check 0",new Integer(0),one.get(1)) ;
         assertEquals("Check 0",new Integer(1),several.get(1)) ;
      }

   /** Test if size of lists are correct */
   @Test
      public void testListSize()
      {
         assertEquals("Check Empty Size",0,empty.size()) ;
         assertEquals("Check One Size",1,one.size()) ;
         assertEquals("Check Several Size",DIM,several.size()) ;
      }

   /** Test setting a specific entry */
   @Test
      public void testSet()
      {
         slist.set(1,"Final");
         assertEquals("Setting specific value", "Final",slist.get(1));
      }

   /** Test isEmpty */
   @Test
      public void testEmpty()
      {
         assertTrue("empty is empty",empty.isEmpty()) ;
         assertTrue("one is not empty",!one.isEmpty()) ;
         assertTrue("several is not empty",!several.isEmpty()) ;
      }

   /** Test out of bounds exception on get */
   @Test
      public void testGetException()
      {
         try 
         {
            empty.get(0);
            // This is how you can test when an exception is supposed 
            // to be thrown
            fail("Should have generated an exception");  
         }
         catch(IndexOutOfBoundsException e)
         {
            //  normal
         }
      }


   /** Test iterator on empty list and several list */
   @Test
      public void testIterator()
      {
         int counter = 0 ;
         for (iter = (DoublyLinkedList12<Integer>.MyListIterator)empty.listIterator() ; iter.hasNext(); )
         {
            fail("Iterating empty list and found element") ;
         }
         counter = 0 ;
         for (iter = (DoublyLinkedList12<Integer>.MyListIterator)several.listIterator() ; iter.hasNext(); iter.next())
            counter++;
         assertEquals("Iterator several count", counter, DIM);

      }

   @Test
      public void testSize(){
         assertEquals("Check Empty Size",0,empty.size()) ;
         assertEquals("Check One Size",1,one.size()) ;
         assertEquals("Check Several Size",DIM,several.size()) ;

      }  @Test
   public void testGet(){

      assertEquals("Check 0",new Integer(0),one.get(1)) ;
      assertEquals("Check 0",new Integer(1),several.get(1)) ;


   }  @Test
   public void testAdd(){

      assertEquals("First", slist.get(1));
      assertEquals("Last", slist.get(2));


   }  @Test
   public void testAddData(){
      one.add(new Integer(1));
      assertEquals("checking add for one", new Integer(1), one.get(2));


   }  @Test
   public void testSetIndex(){
      one.set(1, new Integer(12));
      assertEquals("checking the set method", new Integer(12), one.get(1));


   }  @Test
   public void testRemove(){

      assertEquals("checking the remove method", new Integer(0), one.remove(1));


   }  @Test
   public void testClear(){
      one.clear();
      assertFalse(one.get(1) == null);



   }  @Test
   public void testIsEmpty(){
      assertTrue("empty is empty",empty.isEmpty()) ;
      assertTrue("one is not empty",!one.isEmpty()) ;
      assertTrue("several is not empty",!several.isEmpty()) ;


   }  @Test
   public void testIteratorAddNext(){
      iter = (DoublyLinkedList12<Integer>.MyListIterator)one.listIterator();
      iter.add(new Integer(1));
      assertEquals("checking the next method", new Integer(1), iter.next());


   }  @Test
   public void testIteratorSet(){
      iter = (DoublyLinkedList12<Integer>.MyListIterator)one.listIterator();
      iter.add(new Integer(1));
      iter.next();
      iter.set(new Integer(12));
      assertEquals("checking set method", new Integer(12), iter.previous());




   }@Test
   public void testIteratorRemove(){
      iter = (DoublyLinkedList12<Integer>.MyListIterator)one.listIterator();
      iter.add(new Integer(1));
      iter.next();
      iter.remove();
      assertEquals("checking the remove method", new Integer(0), one.get(1));

   }
}


