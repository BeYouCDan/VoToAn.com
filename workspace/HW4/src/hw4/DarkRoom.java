package hw4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class DarkRoom implements DarkRoomInterface {

	protected char [][] darkRoom;
    protected int numRows=0;
    protected int numCols;
    protected int countVisits = 0;
    Location[] visitedLoc = new Location[100];


	public void readFromFile(String fname) {

		String line;
		BufferedReader inputStrem;
		StringTokenizer st;


		try {
		    int currentRow = 0;
          // reads the file and put i tas inputStrem
		    inputStrem = new BufferedReader(new FileReader(fname));

		    while ((line = inputStrem.readLine()) != null) {
			if (numRows == 0) {
            //breaks down the tring in to tokens
				st = new StringTokenizer(line);
            //gets the number of rows 
				numRows = Integer.parseInt(st.nextToken());
            //get the number of coloums
				numCols = Integer.parseInt(st.nextToken());
            //creating a darkrrom with the numRows and cols
			    darkRoom = new char[numRows][numCols];
			}
        //if the length of the line is one then exit 
         else if (line.length() == 1)
            
			    break;
         //put all the info into the 2d array
			else {
			    for (int c = 0; c < numCols; c++) {
			    	darkRoom[currentRow][c] = line.charAt(c);
			    }
			    currentRow ++;
			}
		    }
		}
		catch (IOException e) {
			System.out.println (e.toString());
	        System.out.println("Could not find file " + fname);
		}

	}


	//Helper methods:

	// Method that returns the Location of "start"
	public Location findStart()
	{
      int c = 0;
      int d = 0;
      for(int i = 0; i < numRows; i++){
         for(int j= 0; j < numCols; j++){
            if(darkRoom[i][j] == 'S'){
               c = i;
               d = j;
               break;
            }
         }
         break;
         
      }
      Location start = new Location(c, d);
      return start;
		  //CHANGE
	}

	//Method that checks if the goal was found
	public boolean isDoor (Location loc)
	{
      int c = 0;
      int d = 0;
      for(int i = 0; i < numRows; i++){
         for(int j = 0; j < numCols; j++){
            if(darkRoom[i][j] == 'D'){
               c = i;
               d = j;
               break;
            }
         }
         break;
      }
      Location Door = new Location(c, d);
      if(loc == Door){
         return true;
      }
      else{
      
      return false;
      }

	}


	//Method that checks if you can move
	public boolean canMove(Location loc)
	{darkRoom = new char[numRows][numCols];
		
		int row = loc.getRow(); 
      int column = loc.getColumn();
     if(darkRoom[row][column] == '*' || darkRoom[row][column] == '@'){
      return false;
     }
     else{return true;} //CHANGE
	}

	//Marks explored (visited) positions
	public void markVisited (Location loc)
	{
      visitedLoc = new Location[100];
		int row = loc.getRow(); 
      int column = loc.getColumn();
      darkRoom[row][column] = '.';
      visitedLoc[countVisits] = loc;
      countVisits++;
		//CHANGE
	}

	//counts the number of visited positions
	public int countVisited()
	{
		return countVisits; //CHANGE
	}


	// removes marks from visiting (removes '.')
	public void clear()
	{
      for(int i = 0; i < visitedLoc.length; i++){
         int row = visitedLoc[i].getRow();
         int column = visitedLoc[i].getColumn();
         darkRoom[row][column] = ' ';
         countVisits = 0;
      }
		//CHANGE
	}

    //prints your array that represents a room
	public void printRoom()
	{
      for(int i = 0; i < numRows; i++){
         for(int j = 0; j < numCols; j++){
            System.out.println(darkRoom[i][j]);
         }
      }

	  //CHANGE
	}

	// Search for ESCAPE!!!
  public void escapeDarkRoom(String choice){
     Stack_QueueInterface<Location> barnRoom;
     if("Stack".equals(choice)) 
        barnRoom = new MyStack<Location>();
     else
        barnRoom = new MyQueue<Location>();
     Location start = new Location(findStart().getRow(), findStart().getColumn());
     while(true){
        if(isDoor(start.left()) || isDoor(start.up())|| isDoor(start.right())|| isDoor(start.down())){
         break;
        }
        
        /*else if(canMove(start.left())){
        barnRoom.addElement(start.left());
     }*/
        else if(canMove(start.up())){
        barnRoom.addElement(start.up());
     }
        else if( canMove(start.right())){
        barnRoom.addElement(start.right());
     }
        else if(canMove(start.down())){
        barnRoom.addElement(start.down());
     }
     start = barnRoom.removeElement();
     markVisited(start);
     }
     printGoal(choice, countVisited(), barnRoom.size());
     printRoom();
     clear();







  //HAVE FUN!  I did :)


	// remember to call printRoom() after you call printGoal()
  }

	public void printGoal(String choice, int stepsTaken, int positionsLeft)
	{
		System.out.println("Goal found (with " + choice + "): It took "
					+ stepsTaken + " explored positions");
			System.out.println("There is (are) " + positionsLeft
					+ " position(s) left to explore in stack");

	}

}
