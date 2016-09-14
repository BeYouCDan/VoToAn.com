package hw4;
public interface DarkRoomInterface {
	
	//reads the file
	public void readFromFile(String filename); 
	
	// Method that returns the Location of "start"
    public Location findStart();
		
  //Method that checks if the goal was found
  	public boolean isDoor (Location loc);
  	
  //Method that checks if the move is legal
  	public boolean canMove(Location loc);
	
   //Method that marks visited position with a '.'	
  	public void markVisited (Location loc);
  	
  	//clears the room from '.' in order to solve it again. 
	public void clear();
	
	//finds the way out
     public void escapeDarkRoom(String choice);
     
     //counts how many visited (explored) positions it took
     //to find a door.
     public int countVisited();
     
     //prints your char array that represents the room and the moves
     public void printRoom();
	

}
