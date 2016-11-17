/* This class is used to demonstrate exception handling for invalid argument
   numbers.
   Yuge Liang, 28 May 2015
*/
public class InvalidArgCountException extends Exception{
    
    public InvalidArgCountException() {
		super("Incorrect number of arguments supplied to command.");
	}
	
	public InvalidArgCountException(String message) {
		super(message);
	}
}
