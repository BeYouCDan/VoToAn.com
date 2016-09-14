package hw7;
import java.util.*;

public class BSTree{
   BSTNode root;    
   //BSTNode current = root;
   Person[] theNames = new Person[100];
   int i = 0;
   /**
    * This method will take the two arguments and create a Person-object. 
    * If the String  name is empty or if the key is not an int number in the range, 
    * the method will throw an IllegalArgumentException. 
    * Here is where you will validate the information that the Person-object constructor 
    * method will receive. If this method detects any errors then the constructor 
    * for the Person-object and the constructor for the TNode object shouldn't be called.
    * Use a right child to insert a duplicate key. 
    **/

 /*  public boolean findTo(Person thisGuy){
      if(thisGuy.getName().equals(current.getPerson().getName )&& thisGuy.getKey() == current.getPerson().getKey()){
         return true;
      }
      return false;

   }
   public int compareTo(Person thisGuy){
      if(current.getPerson().getKey() < thisGuy.getKey()){
         return -1;

      }
      if(current.getPerson().getKey() > thisGuy.getKey()){
         return 1;
      }
      return 0;

   }
   */




   public void insert( String name, int key){
      if(name == null || key < 1 || key > 200){
         throw new IllegalArgumentException();

      }
      Person insertGuy = new Person(name, key);
      if(root == null){
         root = new BSTNode(null, insertGuy, null);

      }
      insertingToR(insertGuy, root);

      /*if(current == null){
         current = new BSTNode(null, insertGuy, null);

      }
      else if(current.getPerson().compareTo(insertGuy) < 0){
         current = current.getLChild();
         insert(name, key);
      }
      else if(current.getPerson().compareTo(insertGuy) > 0){
         current = current.getRChild();
         insert(name, key);
      }*/
      //current = root;



   }

   private void insertingToR(Person thisGuy, BSTNode thisNode){
	   if(thisNode.compareTo(thisGuy, thisNode) == 0){
		   thisNode.unset();
		   
	   }
      if(thisNode.compareTo(thisGuy,thisNode) < 0){
         if(thisNode.getLChild() == null){
            thisNode.setLChild(new BSTNode(null, thisGuy, null));
         }
         thisNode = thisNode.getLChild();
         insertingToR(thisGuy, thisNode);
      }
      else if(thisNode.compareTo(thisGuy, thisNode) > 0){
         if(thisNode.getRChild() == null){
            thisNode.setRChild(new BSTNode(null, thisGuy, null));
         }
         thisNode = thisNode.getRChild();
         insertingToR(thisGuy, thisNode);
      }
   }

   /**
    * This method looks for a person with the key given by the parameter.  
    * If the person is found the method will return "true". 
    * If the person is not found it will return false.
    * The string name will be used in case that the key is duplicated. This will be the way to find the person.
    * If the String  name is empty or if the key is not an int number in the range, 
    * the method will throw a IllegalArgumentException. 
    * Assume no duplicates (No same key and string).
    **/	
   public boolean findPerson(int key, String name){
      if(name == null || key < 1 || key > 200){
         throw new IllegalArgumentException();

      }
      if(root == null){
         return false;
      }
      Person findGuy = new Person(name, key);
      return findPerR(findGuy, root);



    /*  if(current.getPerson().findTo(findGuy)){
         current = root;
         return true;
      }
      else if(current.getPerson().compareTo(findGuy) < 0){
         current = current.getLChild();
         findPerson(key, name);
      }
      else if(current.getPerson().compareTo(findGuy) > 0){
         current = current.getRChild();
         findPerson(key, name);
      }

      current = root;
      return false;*/


   }	
   private boolean findPerR(Person thisGuy, BSTNode thisNode){
      if(thisNode == null){
         return false;
      }
      else if(thisNode.findTo(thisGuy, thisNode) && thisNode.getDel() == false){
         return true;
      }
      else if(thisNode.compareTo(thisGuy, thisNode) < 0){
         thisNode = thisNode.getLChild();
         findPerR(thisGuy, thisNode);
      }
      else if(thisNode.compareTo(thisGuy, thisNode) > 0){
         thisNode = thisNode.getRChild();
         findPerR(thisGuy, thisNode);
      }
      return false;
      
   }
   public void insertToArray(Person thisGuy){
      theNames[i] = thisGuy;
      i++;

   }


   /**
    * Prints the tree , using INORDER traversal. One Person per line. 
    * Returns an array of values in order. If the tree is empty, throw a nullpointerexception.
    **/
//ask abotu this print to array inorder traversal
   public Person [] printToArray(BSTNode root){
      if(root !=  null){
         printToArray(root.getLChild());
         insertToArray((Person) root.getPerson());
         printToArray(root.getRChild());
      }
      return (Person [])theNames;


   }
   public BSTNode getRoot()
   {
      return root; //or whatever you named the root node
   }

   /**
    * Deletes a person with a given key and name. 
    * If the String  name is empty or if the key is not an int number in the range, 
    * the method will throw a IllegalArgumentException. 
    * Use any deletion method. (lazy is OK)
    **/
   public Person delete(int key, String name){
	   Person deleteGuy = new Person(name, key);
	   return deletion(deleteGuy, root).getPerson();
   }
   private BSTNode deletion(Person thisGuy, BSTNode thisNode){
	   if(thisNode ==  null){
		   
		   return null;
	   }
	   else if(thisNode.findTo(thisGuy, thisNode)){
		   thisNode.setIsDelete();
		   return thisNode;
		   
	   }
	   else if(thisNode.compareTo(thisGuy, thisNode)< 0){
		   thisNode = thisNode.getLChild();
		   deletion(thisGuy, thisNode);
		   
	   }
	   else if(thisNode.compareTo(thisGuy, thisNode) > 0){
		   thisNode = thisNode.getRChild();
		   deletion(thisGuy, thisNode);
		   
	   }
	  return null;
	   
   }

   /**
    * Returns the depth of the tree
    **/
   //ask about finding the depth
   int maxDepth, theDepth;
   public int FindDepth(BSTNode root){
	   if(root.getDel() == true){
		   theDepth--;
	   }
	   if(root != null){
		   theDepth++;
		   if(theDepth > maxDepth){
			   maxDepth = theDepth;
			   
		   }
		   
	   
	   FindDepth(root.getLChild());
	   FindDepth(root.getRChild());
	   theDepth--;
	   }
	   return maxDepth;
   }

   /**
    * Returns the number of nodes that are leaves in the tree.
    **/
   public int leafCount(){
	  return leafCount2(root);
	  
	  
   }
   private int leafCount2(BSTNode thisNode){
		  if(thisNode == null){
			  return 0;
		  }
		  if(thisNode.getLChild() == null && thisNode.getRChild() == null){
			  return 1;
			  
		  }
		  return leafCount2(thisNode.getLChild()) + leafCount2(thisNode.getRChild());
		  
		  
	   }

   /**
    * Return the number of nodes at a given level in the tree. Level 0 is the level of the 
    * root node, level 1 are the (up to 2) children of the root, and so on.
    *Return -1 if no such level exists.
    **/
   public int levelCount(int level){
      if(root == null){
         return -1;
      }
      int Counter = 0;
      int total = 0;
      if(levelCount2(level, root, Counter, total) == 0){
         return 1;
      }
      else{
      return levelCount2(level, root, Counter, total);
      }
   }
   public int levelCount2(int level, BSTNode thisNode, int Counter, int total){
	   if(level == 0){
		   return 1;
		   
	   } 	   
      if(thisNode != null){
		   Counter ++;
		  if(Counter == level){
			  return 1;
			  
		  }
		   total = levelCount2(level, thisNode.getLChild()) + levelCount2(level, thisNode.getRChild());
		   Counter--;
	   }
      if(thisNode.getRChild == null && thisNode.getLChild == null){
      return 0;
      }
	   return total;
	   
   }
   //if this method does not work 
   //http://stackoverflow.com/questions/12879903/binary-tree-counting-nodes-on-a-level



   protected class BSTNode {	
	   boolean isDelete;
      Person per;
      BSTNode lChild;
      BSTNode rChild;

      public BSTNode(BSTNode lChild, Person p, BSTNode rChild){
         this.lChild = lChild;
         this.rChild = rChild;
         per = p;
      }

      /**
       *  Returns a Person object from inside a given node
       **/ 	

      public Person getPerson(){
         return per;
      }
      public void setIsDelete(){
    	  isDelete = true;
    	  
      }
      public void unset(){
    	  isDelete = false;
    	  
      }
      public boolean getDel(){
    	  return isDelete;
    	  
      }
      /**
       *  Returns the left child of a given node
       **/ 
      public BSTNode getLChild(){
         return this.lChild;
      }	
      /**
       *  Returns the right child of a given node
       **/ 
      public BSTNode getRChild(){

         return this.rChild;
      }	
      /**
       *  Sets newLNode as the left child of a node
       **/ 
      public void setLChild( BSTNode newLChild){
         this.lChild = newLChild;

      }
      /**
       *  Sets newRNode as the right child of a node
       **/ 
      public void setRChild( BSTNode newRChild){	
         this.rChild = newRChild;

      }

   public boolean findTo(Person thisGuy, BSTNode current){
      if(thisGuy.getName().equals(current.getPerson().getName())&& thisGuy.getKey() == current.getPerson().getKey()){
         return true;
      }
      return false;

   }
   public int compareTo(Person thisGuy, BSTNode current){
      if(current.getPerson().getKey() < thisGuy.getKey()){
         return -1;

      }
      if(current.getPerson().getKey() > thisGuy.getKey()){
         return 1;
      }
      return 0;





   }

}
}



