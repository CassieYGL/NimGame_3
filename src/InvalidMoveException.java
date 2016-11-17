/* This class is used to demonstrate exception handling for invalid move by 
   human player .
   Yuge Liang, 28/May/2015
*/
public class InvalidMoveException extends Exception {
  
	public InvalidMoveException(String message) {
		super(message);
	}
}
