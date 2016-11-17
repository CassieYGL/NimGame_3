
/**
 * This NimPlayer class incluedes construtors, mutators and accessors, and works
 * to input a player's moving numbers. Yuge Liang 28/May/2015
 */
import java.io.*;
import java.util.*;
import java.math.*;

public class NimHumanPlayer extends NimPlayer implements Serializable {

    transient Scanner reader;

//    /*constructors*/

    public NimHumanPlayer() {

    }

    public NimHumanPlayer(String username, String gname, String fname) {
        super(username, gname, fname);
    }


    public NimHumanPlayer(NimHumanPlayer player) {
        super(player);
    }

    public void setScanner(Scanner keyboard) {

        reader = keyboard;
    }

    //A human palyer's move input when involved in an original Nim game
    public String removeStone(int max, int left) {
        String moveNum = "";
        boolean done = false;
        
        //Handle exceptions if the input is invalid.
        while (!done) {
            try {
                moveNum = reader.nextLine();
                
                if (Integer.parseInt(moveNum) > left) {
                    throw new InvalidMoveException("Invalid move. "
                            + "You must remove between"
                            + " 1 and " + left + " stones.");
                } else if ((((Integer.parseInt(moveNum)) < left 
                        || (Integer.parseInt(moveNum)) == left) 
                        && Integer.parseInt(moveNum) > max) 
                        || Integer.parseInt(moveNum) == 0) {
                    throw new InvalidMoveException("Invalid move. "
                            + "You must remove between"
                            + " 1 and " + max + " stones.");
                } else {
                    done = true;
                }
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
                System.out.println();
                System.out.print(">");
            }
        }
        return moveNum;
    }

    //A human palyer's move input when involved in an advanced Nim game.
    public String advancedMove() {
        String move = "";
        move=reader.nextLine();
         return move;

    }
}
