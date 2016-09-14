package hw1;

/**  
 * @author Your Name
 * @date The date
 * */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;



public class SimpleWar {


   //public static String[] UsedCards = new String[52];

   public static void main( String[] args )
   {
      boolean realStuff = false;
      //int tracker = 1;
      int counter = 0;
      int counter2 = 0;
      double first = 0;
      double second = 0;
      double third = 0;
      String userInput2;
      int[] NumberOfUsedKinds = new int[13]; //Use it to count the number of used KINDS
      //NumberOfUsedKinds[0] = 0;
      String[] UsedCards = new String[52]; //Use it to count used cards
      for(int i = 0; i < UsedCards.length; i++){
         UsedCards[i] = " ";
      }

      Scanner scanner = new Scanner(System.in);

      //Scanner input=new Scanner(System.in); //Scanner object that takes an input from a user 

      String[] Suits = {"Hearts","Diamonds","Spades","Clubs"}; //0,1,2,3 
      String[] Kinds = { "Two","Three","Four","Five", "Six", //0, 1, 2, 3, 4
         "Seven", "Eight", "Nine", "Ten", "Jack",  // 5,6,7,8,9
         "Queen", "King", "Ace"}; //10, 11, 12


      //stores computer moves
      LinkedList<String> computerMoves = new LinkedList<String>(); 

      //stores users moves
      LinkedList<String> userMoves = new LinkedList<String>(); 




      int suitIndex, kindIndex, trueValue; //suit and kind chosen randomly by a computer

      Random randomSuit = new Random();//use random object not math
      Random randomKind = new Random();


      while(true){
         while(true){


            //nested while loops to implement it corrrectly 
            int suitRandom = randomSuit.nextInt(4);


            int kindRandom = randomKind.nextInt(13);


            for(int i = 0; i < UsedCards.length; i++){
               // if(UsedCards[i] != null){
               if(!UsedCards[i].equals(Kinds[kindRandom] + " of "  + Suits[suitRandom])){
                  //if(UsedCards == null){
                  // System.out.println("null");
                  realStuff = true;
               }else{
                  realStuff = false;
               }
               }

               if(realStuff){

                  UsedCards[counter] = Kinds[kindRandom] + " of " + Suits[suitRandom];

                  computerMoves.add(Kinds[kindRandom] + " of " + Suits[suitRandom]); //use the contains method 

                  System.out.println("My Card Is: " + Kinds[kindRandom] + " of " + Suits[suitRandom] );


                  // if(computerMoves.contains())

                  System.out.println("What is you card (kind)? (2-14, -1 to finish the game): ");

                  /* if(tracker == 11){
                     System.out.println("All cards of this type have been played. Pick another one");

                     }*/


                  int userInput = scanner.nextInt();

                  if(userInput == -1){
                     break;
                  }

                  trueValue = userInput - 2;

                  int suitRandom2 = randomSuit.nextInt(4);
                  /*for(int i = 0; i < NumberOfUsedKinds.length; i++){
                    if(NumberOfUsedKinds[i] == (trueValue)){
                    tracker++;
                    System.out.println("here inside the if method for the kinds");
                    }
                    }*/


                  NumberOfUsedKinds[counter2] = trueValue;



                  userMoves.add(Kinds[trueValue] + " of " + Suits[suitRandom2]);

                  System.out.println(Kinds[trueValue] + " of " + Suits[suitRandom2]);

                  if(kindRandom > trueValue){
                     System.out.println("I won");
                     System.out.println();
                     first++;
                  }

                  if(kindRandom < trueValue){
                     System.out.println("You won");
                     System.out.println();
                     second++;

                  }
                  if(kindRandom == trueValue){
                     System.out.println("A tie");
                     System.out.println();
                     third++;
                  }
                  counter++;
                  counter2++;
               }
            }
            double total = first + second + third;
            if(total != 0){
               System.out.println("I won: " + first/total*100 + "%" + "  You Won: " + second/total*100 + "%" + "  We tied: " + third/total*100 + "%");
               System.out.println();
               System.out.println();
               for(int i = 0; i < (int)total; i++ ){
                  System.out.println("My Moves: " + computerMoves.get(i) + " Your Moves: " + userMoves.get(i));
               }

               System.out.println("Play again?");
               userInput2 = scanner.next();
               if(userInput2.equals("n")){
                  System.exit(-1);
               }
            }else{System.exit(-1);}

            }


            //here I need to put some history lines
         }



      }
