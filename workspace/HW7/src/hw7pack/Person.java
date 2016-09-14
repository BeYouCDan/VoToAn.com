package hw7pack;
/**
 * Dae Kyung Kim
 * cs12whz
 * A12741934
 */

public class Person{
   private String theName;
   private int theKey;
   /**
    *   Name must be a non-empty String
    *   The key must be an integer number between 1 and 200
    *   Make sure that the parameters are in the order specified.
    **/
   public Person (String name, int key){
      if(name == null){
         throw new NullPointerException();

      }
      theName = name;
      if(key < 1 || key > 200){
         throw new IllegalStateException();
      }
      theName = name;
      theKey = key;
   }	

   /**
    * Sets the name of a person
    **/
   public void setName(String name){
      theName = name;
   }

   /**
    * Returns the name of a person
    **/
   public String getName(){
      return theName;
   }

   /**
    * Returns the int value of a person's key.
    **/
   public int getKey(){
      return theKey;
   }
  


}


