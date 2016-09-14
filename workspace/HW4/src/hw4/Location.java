package hw4;
//Do not forget to comment
public class Location {

	protected int row;
	protected int column;

	   public Location(int currRow, int currCol) {
	      row = currRow;
	      column = currCol;
	   }

	   public int getRow() {
	      return row;
	   }
	   public int getColumn() {
	      return column;
	   }

	   
	   /* LEFT, UP, RIGHT, DOWN */
	   
	   public Location left() {
		     return new Location(row,column-1);
	   }
	   
	   public Location up() {
	      return new Location(row-1,column);
	   } 
	   
	   public Location right() {
		      return new Location(row,column+1);   
	      
	   }
	   public Location down() {
	      return new Location(row+1,column);
	   }


	
}
