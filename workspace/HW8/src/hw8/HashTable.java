/**
 * Dae Kyung Kim
 * A12741934
 * cs12whz
 */
package hw8;
import java.io.*;
import java.util.StringTokenizer;
/**
 * HashTable class which uses hash functions to store strings
 */
public class HashTable{
   int M = 34000;
   int numFull;
   String[] hashTab = new String[M];
   int expand, load, collided;
   /**
    * private method hashCode which hashes the code using the first four
    * bits and then later combines with the las four bits
    * @param s
    * @return long
    */
   private long hashCode(String s) {
      int intLength = s.length() / 4;
      long sum = 0;
      //first half
      for (int j = 0; j < intLength; j++) {
         char c[] = s.substring(j*4,(j*4)+4).toCharArray();
         long mult = 1;
         for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
         }
      }
      //second half
      char c[] = s.substring(intLength * 4).toCharArray();
      long mult = 1;
      for (int k = 0; k < c.length; k++) {
         sum += c[k] * mult;
         mult *= 256;
      }
      return(Math.abs(sum) % M);
   }

   /**
    * doublehash which just returns the minus of 7 from module
    * @param hash
    * @return int
    */
   private int doubleHash(int hash){
      return 7 - hash % 7;
   }
/**
 * insert method which inserts the given string
 * @param needInsert
 * @return boolean
 */
   public boolean insert(String needInsert){
      if(numFull == M){
         reHash();
      }
      boolean insertBoo;
      //hashing begins
      int hash = (int) hashCode(needInsert);
      int doubleHash = doubleHash(hash);
      while(true){

         if(hashTab[hash] == (null) || hashTab[hash].equals("true")){
            numFull++;
            hashTab[hash]  = needInsert;
            insertBoo = true;
            break;
         }
         //checks if it already has the value or not if it does than false
         else if (hashTab[hash].equals(needInsert)){
            insertBoo = false;
            break;
         }
         //rehashing and coliding
         else{
            collided++;
            hash += doubleHash;
            if(hash > M - 1){
               reHash();

            }
         }
      }
      return insertBoo;


   }	
   /**
    * rehash function which expands and rehashes the value inside the array
    */
   private void reHash(){
      expand++;
      String[] rehashed = new String[hashTab.length * 2];
      M = hashTab.length*2;
      for(int i = 0; i < hashTab.length; i++){
         rehashed[i] = hashTab[i];
      }
      //setting the hashtab equal
      hashTab = rehashed;
   }
   /**
    * look up the string value passed in and checks if it has it or not
    * @param needLookup
    * @return boolean
    */
   public boolean lookup(String needLookup){
      boolean lookupBoo;
      int hash = (int)hashCode(needLookup);
      int doubleHash = doubleHash(hash);
      while(true){
    	  //found null
         if(hashTab[hash] ==  null){
            lookupBoo = false;
            break;
         }
         //found it
         else if(hashTab[hash].equals(needLookup)){
            lookupBoo = true;
            break;
         }
         //rehashing
         else{
            hash += doubleHash;
            if(hash > M - 1){
               System.out.println("item not found");
               lookupBoo = false;
               break;

            }
         }
      }
      return lookupBoo;
   }
   
   /**
    * delete method which searches and makes a lazy deletion
    * @param needDelete
    * @return boolean
    */
   public boolean delete(String needDelete){
      boolean deleteBoo;
      int hash = (int)hashCode(needDelete);
      int doubleHash = doubleHash(hash);
      while(true){
    	  //found null
         if(hashTab[hash] == (null)){
            deleteBoo = false;
            break;
         }
         //deleting
         else if(hashTab[hash].equals(needDelete)){
            hashTab[hash] = "true";
            deleteBoo = true;
            break;
         }
         //rehashing
         else{
            hash += doubleHash;
            if(hash > M - 1){
               deleteBoo = false;
               break;
            }
         }
      }
      return deleteBoo;
   }
   /**
    * print the array
    */
   public void print(){
      int i;
      for( i = 0; i < hashTab.length; i++){
         System.out.println(i + ": " + hashTab[i]);
      }
      load = i;
   }
   /**
    * checks the input and puts in the input accordingly
    * @param args
    * @throws NumberFormatException
    * @throws IOException
    */
   public static void main(String[] args)throws NumberFormatException, IOException{
      String line, input, status;
      BufferedReader inputStrem;
      StringTokenizer st;


      if(args.length != 1)
      {
         System.err.println("Incorrect number of arguments: "+args.length);
         System.exit(1); 
      }
      File file = new File(args[0]);
      HashTable hashTable = new HashTable();

      try{

         inputStrem = new BufferedReader(new FileReader(file));

         while ((line = inputStrem.readLine()) != null) {
            st = new StringTokenizer(line);
            if(st.hasMoreTokens()){

               status = st.nextToken();
               input = st.nextToken().toLowerCase();
               if(status.equals("insert")){
            	   //dropping the quotes
                  String newInput = input.substring(1, input.length() - 1);
                  if(hashTable.insert(newInput)){
                     System.out.println("item successfully inserted");
                  }
                  else{
                     System.out.println("item already present");
                  }

               }
               if(status.equals("lookup")){
            	   //dropping the quotes
                  String newInput = input.substring(1, input.length() - 1);
                  if(hashTable.lookup(newInput)){
                     System.out.println("item found");
                  }
                  else{
                     System.out.println("item not found");
                  }

               }
               if(status.equals("delete")){
            	   //dropping the quotes
                  String newInput = input.substring(1, input.length() - 1);

                  if(hashTable.delete(newInput)){
                     System.out.println("item deleted");
                  }
                  else{
                     System.out.println("item not found");
                  }
               }
               if(status.equals("print")){
                  hashTable.print();
               }

            }
         }
         System.out.println("this is exapand " + hashTable.expand);
         System.out.println("this is load" + hashTable.load);
         System.out.println("thsi is collide" + hashTable.collided);
      }finally{}

   }

}





