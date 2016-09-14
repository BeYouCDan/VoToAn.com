package hw4;
public class Escape{
   public static void main(String[] args){
      DarkRoom darkBarnYard = new DarkRoom();
      darkBarnYard.readFromFile("smallRoom.txt");
      darkBarnYard.escapeDarkRoom("Stack");
   }
}
