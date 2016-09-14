/**
 * Dae Kyung Kim
 * cs12whz
 * A12741934
 */
package hw7pack;
import java.util.*;

public class BSTree{
   BSTNode root;    
   /**
    * This method will take the two arguments and create a Person-object. 
    * If the String  name is empty or if the key is not an int number in the range, 
    * the method will throw an IllegalArgumentException. 
    * Here is where you will validate the information that the Person-object constructor 
    * method will receive. If this method detects any errors then the constructor 
    * for the Person-object and the constructor for the TNode object shouldn't be called.
    * Use a right child to insert a duplicate key. 
    **/
   public void insert( String name, int key){
      if(name == null || key < 1 || key > 200){
         throw new IllegalArgumentException();

      }
      Person insertGuy = new Person(name, key);
      if(root == null){
         root = new BSTNode(null, insertGuy, null);

      }
      insertingToR(insertGuy, root);


   }
/**
 * insertToR which is a reculsive helper method to insert the person in the correct
 * Location
 * @param thisGuy
 * @param thisNode
 */
   private void insertingToR(Person thisGuy, BSTNode thisNode){
	   if(thisNode.compareTo(thisGuy, thisNode) == 0){
		   thisNode.unset();//if the isdelete is true then make it false
		   
	   }
	   //this guy smaller
      if(thisNode.compareTo(thisGuy,thisNode) < 0){
         if(thisNode.getLChild() == null){//base case
            thisNode.setLChild(new BSTNode(null, thisGuy, null));
         }
         thisNode = thisNode.getLChild();
         insertingToR(thisGuy, thisNode);
      }
      //this guy greater
      else if(thisNode.compareTo(thisGuy, thisNode) > 0){
         if(thisNode.getRChild() == null){//base case
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


   }	
   /**
    * helper find reculsive method which return true if the node is found
    * false if it didn't
    * @param thisGuy
    * @param thisNode
    * @return boolean
    */
   private boolean findPerR(Person thisGuy, BSTNode thisNode){
      if(thisNode == null){
         return false;
      }
      else if(thisNode.findTo(thisGuy, thisNode) && thisNode.getDel() == false){
         return true;
      }
      else if(thisNode.compareTo(thisGuy, thisNode) < 0){
         thisNode = thisNode.getLChild();
         return findPerR(thisGuy, thisNode);
      }
      else{
         thisNode = thisNode.getRChild();
         return findPerR(thisGuy, thisNode);
      }
      
   }
/**
 * collide array which takes in both the left and the right and the root array 
 * and "colide them together" as appropriate
 * @param left
 * @param root
 * @param right
 * @return
 */
   public Person[] collideArray(Person[] left, Person[] root, Person[] right){
      if(left == null){//colide root and right
    	  Person[] leftWR = new Person[right.length + 1];
    	  leftWR[0] = root[0];
    	  for(int i = 1; i < leftWR.length; i++){
    		  leftWR[i] = right[i-1];
    	  }
    	  return leftWR;
      }
      else if(right == null){//collide left and root
    	  Person[] leftWL = new Person[left.length + 1];
    	  for(int i = 0; i < leftWL.length - 1; i++){
    		  leftWL[i] = left[i];
    	  }
    	  leftWL[leftWL.length - 1] = root[0];
    	  return leftWL;
      }
      else{//else collide all
    	  Person[] leftWLR = new Person[left.length + 1 + right.length];
    	  for(int i = 0; i < left.length ; i++){
    		  leftWLR[i] = left[i];
    	  }
    	  leftWLR[left.length] = root[0];
    	  for(int i = left.length + 1, y = 0; i < leftWLR.length && y < right.length; i++, y++){
    		  leftWLR[i] = right[y];
    	  }
    	  return leftWLR;
    	  
      }

   }


   /**
    * Prints the tree , using INORDER traversal. One Person per line. 
    * Returns an array of values in order. If the tree is empty, throw a nullpointerexception.
    **/
   public Person [] printToArray(BSTNode root){
      if(root !=  null){
    	  //creating an array of left child
    	Person[] leftChild = printToArray(root.getLChild());
    	//creating an array of right child
         Person [] rightChild = printToArray(root.getRChild());
         //an array which includes the root as one and only thing that is inside
         Person[] theRoot = new Person[1];
    	 theRoot[0] = root.getPerson();
    	          
         if(leftChild == null && rightChild == null){
        	 
        	 return theRoot;
         }
         //collide the arrays together
         return collideArray(leftChild, theRoot, rightChild);
         
      }
      else{
    	  return null;
      }
      

   }
   /**
    * getting the root
    * @return BSTNode
    */
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
	   return deletion(deleteGuy, root);
   }
   /**
    * helper reculsive method for delete which finds the node to delte and changes it's
    * boolean to true
    * @param thisGuy
    * @param thisNode
    * @return Person
    */
   private Person deletion(Person thisGuy, BSTNode thisNode){
	   if(thisNode ==  null){//base case if the root hits null
		   
		   return null;
	   }
	   //found the node
	   else if(thisNode.findTo(thisGuy, thisNode)){
		   thisNode.setIsDelete();//deleting
		   return thisNode.getPerson();
		   
	   }
	   else if(thisNode.compareTo(thisGuy, thisNode)< 0){
		   thisNode = thisNode.getLChild();
		   return deletion(thisGuy, thisNode);//deltion will be null if not found
		   
	   }
	   else{
		   thisNode = thisNode.getRChild();
		   return deletion(thisGuy, thisNode);//deletion will be null if not found
		   
	   }
	  
   }

  /**
   * returns the depth of the root passed on as the parameter
   * @param root
   * @return int
   */
   public int FindDepth(BSTNode root){
	   if(root == null){
		   return 0;
	   }
	   return depthHelper(0, root, this.root);
   }
   /**
    * helper finddepth method which finds the depth of the the passed in node
    * @param depth
    * @param findThis
    * @param root
    * @return int
    */
   private int depthHelper(int depth, BSTNode findThis, BSTNode root){
	   if(root.findTo(findThis.getPerson(), root) && root.getDel() == false){
		   return depth;
	   }
	   else if(root.compareTo(findThis.getPerson(), root) < 0){
		   depth++;//depth is incremented every time when the root goes down
		   root = root.getLChild();
		   return depthHelper (depth, findThis, root);
	   }
	   else{
		   depth++;//incresed every time when it goes down.
		   root = root.getRChild();
		   return depthHelper(depth, findThis, root);
	   }
	   
   }

   /**
    * Returns the number of nodes that are leaves in the tree.
    **/
   public int leafCount(){
	  return leafCount2(root);
	  
	  
   }
   private int leafCount2(BSTNode thisNode){
	   	 // if the passed in node is null then i need to return 0.
		  if(thisNode == null){
			  return 0;
		  }
		  //when the leaf is found return 1
		  if(thisNode.getLChild() == null && thisNode.getRChild() == null){
			  return 1;
			  
		  }
		  // the right and the left is added together
		  //the reason why this works is because there wil only be one leaf method in
		  //the farthest part of the node, there will never be overlapping
		  //node
		
		  return leafCount2(thisNode.getLChild()) + leafCount2(thisNode.getRChild());
		  
		  
	   }

   /**
    * Return the number of nodes at a given level in the tree. Level 0 is the level of the 
    * root node, level 1 are the (up to 2) children of the root, and so on.
    *Return -1 if no such level exists.
    **/
   public int levelCount(int level){
	   //if the root does not exist
      if(root == null){
         return -1;
      }
      //if the level does not exist
      if(levelCount2(level, root) == 0){
         return -1;
      }
      //if the level is found and return the node amount
      else{
      return levelCount2(level, root);
      }
   }
   /**
    * reculsive method to find the amount of node int that level
    * @param level
    * @param thisNode
    * @return int
    */
   private int levelCount2(int level, BSTNode thisNode){
	   if(thisNode == null){
		   return 0;
		   }
   
	   if(level == 0){//when the level is found
		   return 1;
		   
	   }
	   else{
		   //pass in level -1 so that it eventually reaches 0
		   return levelCount2(level - 1, thisNode.getLChild()) + levelCount2(level - 1, thisNode.getRChild());
	   }
      
	   
   }
   
   
   
   
   /**
    * Bst Node class which create the node needed for the tree
    */
   protected class BSTNode {	
	   boolean isDelete;
      Person per;
      BSTNode lChild;
      BSTNode rChild;
/**
 * the constructor
 * @param lChild
 * @param p
 * @param rChild
 */
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
      /**
       * set the delete to true
       */
      public void setIsDelete(){
    	  isDelete = true;
    	  
      }
      /**
       * the the is delte to false
       */
      public void unset(){
    	  isDelete = false;
    	  
      }
      /**
       * returns the current state of delete
       * @return boolean
       */
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
/**
 * find to method which checks if the passed in person matches the massed in
 * nods's person
 * @param thisGuy
 * @param current
 * @return boolean
 */
   public boolean findTo(Person thisGuy, BSTNode current){
      if(thisGuy.getName().equals(current.getPerson().getName())&& thisGuy.getKey() == current.getPerson().getKey()){
         return true;
      }
      return false;

   }
   /**
    * compare to method which is same thing as compare to in the comparable interface
    * compares the key
    * @param thisGuy
    * @param current
    * @return
    */
   public int compareTo(Person thisGuy, BSTNode current){
      if(current.getPerson().getKey() > thisGuy.getKey()){
         return -1;

      }
      else if(current.getPerson().getKey() < thisGuy.getKey()){
         return 1;
      }
      return 0;





   }

}
}



