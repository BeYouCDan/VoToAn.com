/**
 * Dae Kyung Kim
 * A12741934
 * cs12whz
 */
package hw8;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.StringTokenizer;
/**
 * spellchecker class which checks the spelling of the passed words
 * inside the passed in dictionary
 */
public class SpellChecker {
   BufferedReader inputStrem;
   HashTable hashTable = new HashTable();
   /**
    * read dictionary which inputs the value passed in by the argument inside 
    * the main
    * @param reader
    * @throws IOException
    */
   public void readDictionary(Reader reader) throws IOException {
      StringTokenizer st;
      String status, input, line;
      inputStrem = new BufferedReader(reader);
      while((line = inputStrem.readLine()) != null){
         st = new StringTokenizer(line);
         //making everything lower case
         status = st.nextToken().toLowerCase();
         hashTable.insert(status);


      }

   }
   /**
    * checkword which changes each character and checks if it is
    * inside the dictionary or not
    * @param word
    * @return
    */
   public String[] checkWord(String word) {
      if(hashTable.lookup(word) == false){
         char[] possible = word.toLowerCase().toCharArray();
         ArrayList<String> chance = new ArrayList<String>();
         //String[] chance = new String[possible.length];
         for(int i = 0; i < possible.length; i++){
            possible[i] = 'a';
            String newWord = new String(possible);
            if(hashTable.lookup(newWord)){
               chance.add(newWord);
            }
            possible[i] = 'b';
            String newWord1 = new String(possible);
            if(hashTable.lookup(newWord1)){
               chance.add(newWord);				}
            possible[i] = 'c';
            String newWord2 = new String(possible);
            if(hashTable.lookup(newWord2)){
               chance.add(newWord2);
            }
            possible[i] = 'd';
            String newWord3 = new String(possible);
            if(hashTable.lookup(newWord3)){
               chance.add(newWord3);
            }
            possible[i] = 'e';
            String newWord4 = new String(possible);
            if(hashTable.lookup(newWord4)){
               chance.add(newWord4);
            }
            possible[i] = 'f';
            String newWord5 = new String(possible);
            if(hashTable.lookup(newWord5)){
               chance.add(newWord5);
            }
            possible[i] = 'g';
            String newWord6 = new String(possible);
            if(hashTable.lookup(newWord6)){
               chance.add(newWord6);
            }
            possible[i] = 'h';
            String newWord7 = new String(possible);
            if(hashTable.lookup(newWord7)){
               chance.add(newWord7);
            }
            possible[i] = 'i';
            String newWord8 = new String(possible);
            if(hashTable.lookup(newWord8)){
               chance.add(newWord8);
            }
            possible[i] = 'j';
            String newWord9 = new String(possible);
            if(hashTable.lookup(newWord9)){
               chance.add(newWord9);
            }
            possible[i] = 'k';
            String newWord10 = new String(possible);
            if(hashTable.lookup(newWord10)){
               chance.add(newWord10);
            }
            possible[i] = 'l';
            String newWord11 = new String(possible);
            if(hashTable.lookup(newWord11)){
               chance.add(newWord11);
            }
            possible[i] = 'n';
            String newWord12 = new String(possible);
            if(hashTable.lookup(newWord12)){
               chance.add(newWord12);
            }
            possible[i] = 'm';
            String newWord13 = new String(possible);
            if(hashTable.lookup(newWord13)){
               chance.add(newWord13);
            }
            possible[i] = 'o';
            String newWord14 = new String(possible);
            if(hashTable.lookup(newWord14)){
               chance.add(newWord14);
            }
            possible[i] = 'p';
            String newWord15 = new String(possible);
            if(hashTable.lookup(newWord15)){
               chance.add(newWord15);
            }
            possible[i] = 'q';
            String newWord16 = new String(possible);
            if(hashTable.lookup(newWord16)){
               chance.add(newWord16);
            }
            possible[i] = 'r';
            String newWord17 = new String(possible);
            if(hashTable.lookup(newWord17)){
               chance.add(newWord17);
            }
            possible[i] = 's';
            String newWord18 = new String(possible);
            if(hashTable.lookup(newWord18)){
               chance.add(newWord18);
            }
            possible[i] = 't';
            String newWord19 = new String(possible);
            if(hashTable.lookup(newWord19)){
               chance.add(newWord19);
            }
            possible[i] = 'u';
            String newWord20 = new String(possible);
            if(hashTable.lookup(newWord20)){
               chance.add(newWord20);
            }
            possible[i] = 'v';
            String newWord21 = new String(possible);
            if(hashTable.lookup(newWord21)){
               chance.add(newWord21);
            }
            possible[i] = 'w';
            String newWord22 = new String(possible);
            if(hashTable.lookup(newWord22)){
               chance.add(newWord22);
            }
            possible[i] = 'x';
            String newWord23 = new String(possible);
            if(hashTable.lookup(newWord23)){
               chance.add(newWord23);
            }
            possible[i] = 'y';
            String newWord24 = new String(possible);
            if(hashTable.lookup(newWord24)){
               chance.add(newWord24);
            }
            possible[i] = 'z';
            String newWord25 = new String(possible);
            if(hashTable.lookup(newWord25)){
               chance.add(newWord25);
            }
            	//putting back the word because it does not exist
               possible[i] = word.charAt(i);
         }
         //making a string array
         String[] arr = new String[chance.size()];
         //if there is a word inside the array
         if(chance.size() != 0){
            arr = chance.toArray(arr);
            return arr;
         } else{
            System.out.println(word);
            return arr;
         }
      }
      else{

         return null;
      }
   }
/**
 * using the two methods above to succesffuly find out the spell variants or no
 * variants
 * @param args
 * @throws IOException
 */
   public static void main(String[] args) throws IOException  // This is used for Part 2
   {
      String spellCheck, line;
      String outPut = " ";
      BufferedReader inputStrem;
      StringTokenizer st;
      if(args.length < 1)
      {
         System.err.println("Incorrect number of arguments: "+args.length);
         System.exit(1); 
      }
      File file = new File(args[0]);
      File secondFile = new File(args[1]);
      //need the below method to conver from file to reader
      FileReader reader = new FileReader(file);
      SpellChecker checker = new SpellChecker();
      checker.readDictionary(reader);
      inputStrem = new BufferedReader(new FileReader(secondFile));
      while((line = inputStrem.readLine()) != null){
         st = new StringTokenizer(line);
         spellCheck = st.nextToken();
         String[] suggestion = checker.checkWord(spellCheck);
         if(suggestion != null){
            if(suggestion.length != 0){
            	//the word is same
               if(suggestion[0].equals(spellCheck)){
                  System.out.println(suggestion[0]);
               }
               //there are variants than to this
               else{
                  for(int i = 0; i < suggestion.length; i++){
                     outPut = outPut + suggestion[i] + " ";
                  }
                  System.out.println(spellCheck + ":"+ outPut);
               }

            }
         }
      }




   }

}




